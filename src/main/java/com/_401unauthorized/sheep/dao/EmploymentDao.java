package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmploymentDao {
    boolean write(BoardDto employmentDto);
    void insert_job(BoardDto jobDto);
    List<BoardDto> list();
    BoardDto detail(Integer board_number);
}
