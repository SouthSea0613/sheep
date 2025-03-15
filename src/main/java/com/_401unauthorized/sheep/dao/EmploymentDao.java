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
    boolean resume_write2(BoardDto boarddto);
    boolean complete(Integer board_number);
    List<BoardDto> resume_list(Integer board_number);
    BoardDto profile_detail(Integer board_number);
    boolean lets_do_it(Integer board_number);
}
