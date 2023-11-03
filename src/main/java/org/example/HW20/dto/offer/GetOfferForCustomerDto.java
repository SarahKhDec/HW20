package org.example.HW20.dto.offer;

import org.example.HW20.dto.expert.GetExpertForCustomerDto;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetOfferForCustomerDto {

    Long id;
    LocalDateTime registerDateAndTime;
    Long proposedPrice;
    LocalDateTime suggestedTime;
    LocalDateTime durationOfWork;
    GetExpertForCustomerDto expert;
}
