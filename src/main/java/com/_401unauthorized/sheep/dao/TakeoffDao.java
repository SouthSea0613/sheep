package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeoffDao {
    boolean call(ApplyDto applyDto);
    WishDto get_wish_detail(Integer wish_number, String user_id);
    boolean write(TakeoffSellerDto takeoffsellerDto);
    boolean status(String user_id, String wish_number);
    List<CategoryDto> get_category_detail(Integer wish_number, String user_id);
    List<TakeoffDto> list(Integer wish_number);
    List<TakeoffDto> my_list(String userid);
    boolean complete(Integer wish_number);
    Integer counttakeoff(String wish_number);
    boolean update_status(String wish_number);
}
