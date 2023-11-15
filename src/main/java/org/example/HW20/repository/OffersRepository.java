package org.example.HW20.repository;

import org.example.HW20.dto.offer.OfferByScorePriceDto;
import org.example.HW20.entity.Offers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface OffersRepository extends JpaRepository<Offers, Long> {

    @Query("select new org.example.HW20.dto.offer.OfferByScorePriceDto(o.id, o.expert.score, o.expert.email, o.proposedPrice)" +
            " from Offers o where o.order.id= :orderId order by o.proposedPrice asc ")
    List<OfferByScorePriceDto> findByOrderIdOrderByProposedPrice(Long orderId);

    @Query("select new org.example.HW20.dto.offer.OfferByScorePriceDto(o.id, o.expert.score, o.expert.email, o.proposedPrice)" +
            " from Offers o where o.order.id= :orderId order by o.expert.score desc ")
    List<OfferByScorePriceDto> findByOrderIdOrderByExpertScore(Long orderId);

    @Query("select o from Offers o where o.id= ?1 and o.order.id= ?2")
    Optional<Offers> findByIdAndOrderId(Long id, Long orderId);
}
