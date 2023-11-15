package org.example.HW20.controller;

import org.example.HW20.dto.offer.*;
import org.example.HW20.dto.order.GetOrderByCustomerEmailDto;
import org.example.HW20.dto.order.GetOrderWithOfferDto;
import org.example.HW20.entity.Offers;
import org.example.HW20.mappers.OfferMapper;
import org.example.HW20.mappers.OrderMapper;
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
    private final OfferMapper offerMapper;
    private final OrderMapper orderMapper;

    @PostMapping("/create")
    public GetOfferDto create(@Valid @RequestBody CreateOfferDto createOfferDto) {
        Offers offers = offerMapper.offerDtoToOffer(createOfferDto);
        return offerMapper.offerToDto(offerService.create(offers));
    }

    @PostMapping("/findAll/orderByProposedPrice")
    public List<OfferByScorePriceDto> orderByProposedPrice(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Offers offers = offerMapper.offerCustomerDtoToOffer(getOrderByCustomerEmailDto);
        return (offerService.orderByProposedPrice(offers, getOrderByCustomerEmailDto.getId()));
    }

    @PostMapping("/findAll/orderByScore")
    public List<OfferByScorePriceDto> orderByScore(@Valid @RequestBody GetOrderByCustomerEmailDto getOrderByCustomerEmailDto) {
        Offers offers = offerMapper.offerCustomerDtoToOffer(getOrderByCustomerEmailDto);
        System.out.println(offerService.orderByScore(offers, getOrderByCustomerEmailDto.getId()));
        return (offerService.orderByScore(offers, getOrderByCustomerEmailDto.getId()));
    }

    @PostMapping("/selectOffer")
    public GetOrderWithOfferDto selectOffer(@Valid @RequestBody SelectOfferDto selectOfferDto) {
        Offers offers = offerMapper.selectOfferDtoToOffer(selectOfferDto);
        return orderMapper.orderToOfferDto(offerService.selectOffer(offers));
    }
}
