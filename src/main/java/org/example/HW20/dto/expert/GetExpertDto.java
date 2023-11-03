package org.example.HW20.dto.expert;

import org.example.HW20.entity.enumeration.ExpertStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetExpertDto {

    Long id;
    String firstname;
    String lastname;
    LocalDateTime registerDate;
    ExpertStatus status;
    Long credit;
    Integer score;
    String role;
}
