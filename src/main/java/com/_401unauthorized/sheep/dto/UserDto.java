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
public class UserDto {
    String user_id;
    String user_pw;
    String user_name;
    String user_phone_company;
    String user_phone;
    String user_addr;
    String user_email;
    String user_type;
    String seller_regnum;
    String seller_company;
    String seller_name;
    String seller_addr;
    String engineer_regnum;
    String engineer_regdate;
    String seller_regnum;
    String seller_company;
    String seller_name;
    String seller_addr;
}
