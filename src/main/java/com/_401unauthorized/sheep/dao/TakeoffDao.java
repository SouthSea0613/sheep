package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.dto.TakeoffSellerDto;
import com._401unauthorized.sheep.dto.CategoryDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeoffDao {
    boolean call(ApplyDto applyDto);
    boolean write(TakeoffSellerDto takeoffsellerDto);
    boolean status(String user_id, String wish_number);
    List<CategoryDto> get_category_detail(Integer wish_number);
}
