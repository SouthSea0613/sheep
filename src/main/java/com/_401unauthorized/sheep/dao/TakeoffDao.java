package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeoffDao {
    boolean call(ApplyDto applyDto);
    WishDto get_wish_detail(Integer wish_number);
    boolean write(TakeoffSellerDto takeoffsellerDto);
    boolean status(String user_id, String wish_number);
    List<CategoryDto> get_category_detail(Integer wish_number);
    List<TakeoffDto> list(Integer wishNumber);

    List<TakeoffDto> my_list(String userid);
<<<<<<< HEAD
    boolean complete(Integer wish_number);
=======

    Integer counttakeoff(String wishNumber);

    boolean updatestatus(String wishNumber);
>>>>>>> 31d63d6d9c58ef1c132e61806f16cb791a989a08
}
