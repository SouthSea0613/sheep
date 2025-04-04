package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishDao {
    List<WishDto> get_wish_list(String user_id);
    boolean insert_wish(WishDto wishdto);
    boolean insert_major(MajorDto majorDto);
    boolean insert_sub(SubDto subDto);
    WishDto get_wish_detail(int wish_number);
    List<CategoryDto> get_category_detail(int wish_number);
    int get_apply_count(int wishNumber);
    boolean delete_wish(Integer wish_number);
    boolean delete_major_category(Integer wish_number);
    boolean delete_sub_category(Integer wish_number);
    List<CategoryDto> get_takeoff_detail(Integer wish_number);
    boolean update(WishDto wishdto);

    WishDto get_wish_req_list(String userId);

    WishDto get_status(String user_id, Integer wish_number);

    WishDto get_user_status(String user_id, Integer wish_number);
}
