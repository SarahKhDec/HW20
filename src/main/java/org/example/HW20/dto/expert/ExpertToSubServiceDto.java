package org.example.HW20.dto.expert;

import org.example.HW20.entity.SubService;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ExpertToSubServiceDto {

    Long id;

    @NotNull(message = "the sub service must not be empty.")
    SubService subService;
}
