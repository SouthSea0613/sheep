package com._401unauthorized.sheep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class WishDto {
    int wish_number;
    String user_id;
    String wish_title;
    String wish_type;
    String wish_size;
    String wish_size_text;
    int wish_money;
    String wish_addr;

    List<MajorDto> major_category;
    List<SubDto> sub_category;
}
