package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmploymentDao {
    boolean write(BoardDto employmentDto);

}
