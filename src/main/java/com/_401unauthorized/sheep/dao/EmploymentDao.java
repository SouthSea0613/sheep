package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmploymentDao {
    boolean write(BoardDto employmentDto);
    void insert_count(Integer board_number,Integer job_count);
    List<BoardDto> list(BoardDto boardDto);

    boolean select_area(Integer board_number, String job_area);
}
