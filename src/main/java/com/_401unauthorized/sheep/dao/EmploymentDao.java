package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmploymentDao {
    boolean write(BoardDto employmentDto);
    void insert_job(BoardDto jobDto);
    BoardDto detail(Integer board_number);
    List<BoardDto> get_board_list(int start_index);
    boolean resume_write(BoardDto boarddto);
    boolean resume_write2(int board_number, String user_id, Integer parent_board_number);
    boolean complete(Integer board_number);
    BoardDto resume_detail(Integer board_number);
}
