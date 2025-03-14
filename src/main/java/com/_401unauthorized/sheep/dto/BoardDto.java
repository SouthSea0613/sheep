package com._401unauthorized.sheep.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class BoardDto {
    int board_number;
    String user_id;
    String board_title;
    String board_content;
    String board_status;
    String board_regdate;
    String board_kind;
    
    String job_expiredate;
    int job_count;
    String job_status;
    String job_area;
    String seller_company;

    int parent_board_number;
    int employee_status;
    String user_name;
    String user_phone;
}

