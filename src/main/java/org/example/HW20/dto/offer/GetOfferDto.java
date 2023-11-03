package org.example.HW20.dto.offer;

import org.example.HW20.dto.order.GetOrderForExpertDto;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetOfferDto {

    Long id;
    LocalDateTime registerDateAndTime;
    Long proposedPrice;
    LocalDateTime suggestedTime;
    LocalDateTime durationOfWork;
    GetOrderForExpertDto order;
}
