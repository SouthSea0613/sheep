package com._401unauthorized.sheep.dto;

import java.util.List;

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
public class TakeoffDto {
    String user_id;
    String user_phone;
    Integer wish_number;
    String wish_title;
    String major_category;
    String major_text;
    List<String> sub_category;
    String seller_name;
    String wish_category_seller_answer;
    int wish_category_seller_price;
    String apply_status;
    String seller_company;
}
