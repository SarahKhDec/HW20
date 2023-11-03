package org.example.HW20.dto.comment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCommentForExpertDto {

    Long id;
    Integer score;
}
