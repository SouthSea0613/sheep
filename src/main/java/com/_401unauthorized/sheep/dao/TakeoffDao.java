package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.ApplyDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TakeoffDao {
    void call(ApplyDto applyDto);
}
