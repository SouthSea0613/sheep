package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.dto.CategoryDto;
import com._401unauthorized.sheep.dto.TakeoffDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeoffDao {
    boolean call(ApplyDto applyDto);
    List<CategoryDto> get_category_detail(Integer wish_number);

    List<TakeoffDto> list(Integer wishNumber);

    boolean update_status(Integer wishNumber, String applyStatus);

    List<TakeoffDto> my_list(String userid);
}
