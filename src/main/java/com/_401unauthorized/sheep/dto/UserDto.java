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
    private String user_id;
    private String user_pw;
    private String user_name;
    private String user_phone_company;
    private String user_phone;
    private String user_addr;
    private String user_email;
    private String user_type;

    private String seller_regnum;
    private String seller_company;
    private String seller_name;
    private String seller_addr;

    private String engineer_regnum;
    private String engineer_regdate;
}
