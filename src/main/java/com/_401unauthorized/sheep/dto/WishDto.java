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
    Integer wish_money;
    String wish_addr;
    Integer wish_status;

    List<MajorCategoryContentDto> major_category_content_list;
    List<SubCategoryDto> sub_category_list;
}
