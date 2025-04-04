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
public class CategoryDto {
    String category_number;
    String major_text;
    String category_parent;
    String wish_category_seller_answer;
    int wish_category_seller_price;
}
