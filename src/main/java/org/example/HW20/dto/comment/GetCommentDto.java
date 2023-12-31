package org.example.HW20.dto.comment;

import lombok.*;
import lombok.experimental.FieldDefaults;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetCommentDto {

    Long id;
    Integer score;
    String content;
}
