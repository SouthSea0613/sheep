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

<<<<<<< HEAD
    WishDto detail(int wishNumber);

    List<CategoryDto> category_detail(int wishNumber);
=======
    WishDto get_wish_detail(int wish_number);
    List<CategoryDto> get_category_detail(int wish_number);
>>>>>>> yoonsic2
}
