package org.example.HW20.dto.offer;

import org.example.HW20.entity.Orders;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SelectOfferDto {

    @NotBlank(message = "email cannot be empty.")
    @Email(message = "the email pattern is incorrect.")
    String email;

    @NotNull(message = "the order must not be empty.")
    Orders order;

    @NotNull(message = "the offer id must not be empty.")
    Long id;
}
