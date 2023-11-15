package org.example.HW20.mappers;

import org.example.HW20.dto.offer.CreateOfferDto;
import org.example.HW20.dto.offer.GetOfferDto;
import org.example.HW20.dto.offer.GetOfferForCustomerDto;
import org.example.HW20.dto.offer.SelectOfferDto;
import org.example.HW20.dto.order.GetOrderByCustomerEmailDto;
import org.example.HW20.entity.Offers;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OfferMapper {

    GetOfferDto offerToDto(Offers offers);
    Offers offerDtoToOffer(CreateOfferDto createOfferDto);
    List<GetOfferForCustomerDto> offerListToDtoList(List<Offers> offersList);
    default GetOfferForCustomerDto offerListToDtoList(Offers offers){
        GetOfferForCustomerDto getOfferForCustomerDto = new GetOfferForCustomerDto();
        getOfferForCustomerDto.setDurationOfWork(offers.getDurationOfWork());
        getOfferForCustomerDto.setProposedPrice(offers.getProposedPrice());
        getOfferForCustomerDto.setSuggestedTime(offers.getSuggestedTime());
        getOfferForCustomerDto.setRegisterDateAndTime(offers.getRegisterDateAndTime());
        getOfferForCustomerDto.setExpert(offers.getExpert());
        return getOfferForCustomerDto;
    }

    Offers offerCustomerDtoToOffer(GetOrderByCustomerEmailDto getOrderByCustomerEmailDto);
    Offers selectOfferDtoToOffer(SelectOfferDto selectOfferDto);
}
