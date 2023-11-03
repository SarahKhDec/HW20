package org.example.HW20.service;

import org.example.HW20.entity.Offers;
import org.example.HW20.entity.Orders;

import java.util.List;

public interface OfferService {

    Offers create(Offers offers);
    List<Offers> orderByProposedPrice(Offers offers);
    List<Offers> orderByScore(Offers offers);
    Orders selectOffer(Offers offers);
}
