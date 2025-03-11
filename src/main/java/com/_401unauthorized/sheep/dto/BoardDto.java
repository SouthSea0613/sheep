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
    LocalDateTime board_regdate;
    String board_kind;

    LocalDate job_expiredate;
    int job_count;
    String job_status;

    Integer list_count;
    Integer start_index;
    Integer page_number;


}
