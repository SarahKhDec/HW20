package org.example.HW20.service;

import org.example.HW20.dto.offer.OfferByScorePriceDto;
import org.example.HW20.entity.Offers;
import org.example.HW20.entity.Orders;

import java.util.List;

public interface OfferService {

    Offers create(Offers offers);
    List<OfferByScorePriceDto> orderByProposedPrice(Offers offers, Long id);
    List<OfferByScorePriceDto> orderByScore(Offers offers, Long id);
    Orders selectOffer(Offers offers);
}
