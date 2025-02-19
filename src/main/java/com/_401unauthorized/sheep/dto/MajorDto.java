package com._401unauthorized.sheep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class MajorDto {
<<<<<<< HEAD
    String category_number;
    int wish_number;
=======
    int wish_number;
    String category_number;
>>>>>>> user_eunhye
    String major_text;
}
