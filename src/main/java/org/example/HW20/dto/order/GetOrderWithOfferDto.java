package org.example.HW20.dto.order;

import org.example.HW20.dto.offer.GetOfferForCustomerDto;
import org.example.HW20.entity.enumeration.OrderStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetOrderWithOfferDto {

    Long id;
    Long proposedPrice;
    String description;
    LocalDateTime dateAndTime;
    String address;
    OrderStatus orderStatus;
    GetOfferForCustomerDto offerSelected;
}
