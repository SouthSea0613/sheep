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
public class TakeoffSellerDto {
    String user_id;
    String category_number;
    String wish_number;
    String wish_category_seller_answer;
    String wish_category_seller_price;
}
