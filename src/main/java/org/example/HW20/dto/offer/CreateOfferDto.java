package org.example.HW20.dto.offer;

import org.example.HW20.entity.Orders;
import jakarta.validation.constraints.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateOfferDto {

    @NotBlank(message = "email cannot be empty.")
    @Email(message = "the email pattern is incorrect.")
    String email;

    @Positive(message = "proposed price cannot be negative or zero.")
    @NotNull(message = "proposed price cannot be empty.")
    @Digits(integer = 20, fraction = 0, message = "the proposed price must be a number")
    Long proposedPrice;

    @NotBlank(message = "suggested time cannot be empty.")
    String suggestedDate;

    @NotBlank(message = "duration of work cannot be empty.")
    String workTime;

    @NotNull(message = "the order must not be empty.")
    Orders order;
}
