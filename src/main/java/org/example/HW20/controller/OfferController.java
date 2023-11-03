package org.example.HW20.controller;

import org.example.HW20.dto.offer.CreateOfferDto;
import org.example.HW20.dto.offer.GetOfferDto;
import org.example.HW20.dto.offer.GetOfferForCustomerDto;
import org.example.HW20.dto.offer.SelectOfferDto;
import org.example.HW20.dto.order.GetOrderByCustomerEmailDto;
import org.example.HW20.dto.order.GetOrderWithOfferDto;
import org.example.HW20.entity.Offers;
import org.example.HW20.mappers.OfferMapperImpl;
import org.example.HW20.mappers.OrderMapperImpl;
import org.example.HW20.service.OfferServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/offer")
public class OfferController {

    private final OfferServiceImpl offerService;
    private final OfferMapperImpl offerMapper;
    private final OrderMapperImpl orderMapper;

    @PostMapping("/create")
    public GetOfferDto create(@Valid @RequestBody CreateOfferDto createOfferDto) {
        Offers offers = offerMapper.offerDtoToOffer(createOfferDto);
        return offerMapper.offerToDto(offerService.create(offers));
    }

    @PostMapping("/findAll/orderByProposedPrice")
    public List<GetOfferForCustomerDto> orderByProposedPrice(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Offers offers = offerMapper.offerCustomerDtoToOffer(getOrderByCustomerEmailDto);
        return offerMapper.offerListToDtoList(offerService.orderByProposedPrice(offers));
    }

    @PostMapping("/findAll/orderByScore")
    public List<GetOfferForCustomerDto> orderByScore(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Offers offers = offerMapper.offerCustomerDtoToOffer(getOrderByCustomerEmailDto);
        return offerMapper.offerListToDtoList(offerService.orderByScore(offers));
    }

    @PostMapping("/selectOffer")
    public GetOrderWithOfferDto selectOffer(@Valid @RequestBody SelectOfferDto selectOfferDto) {
        Offers offers = offerMapper.selectOfferDtoToOffer(selectOfferDto);
        return orderMapper.orderToOfferDto(offerService.selectOffer(offers));
    }
}
