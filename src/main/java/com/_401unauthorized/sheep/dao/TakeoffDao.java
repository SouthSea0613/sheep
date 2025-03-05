package com._401unauthorized.sheep.dao;

<<<<<<< HEAD
import com._401unauthorized.sheep.dto.*;
=======
import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.dto.TakeoffSellerDto;
import com._401unauthorized.sheep.dto.CategoryDto;
>>>>>>> yoonsic2
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeoffDao {
    boolean call(ApplyDto applyDto);
<<<<<<< HEAD
    WishDto get_wish_detail(Integer wish_number);
=======
    boolean write(TakeoffSellerDto takeoffsellerDto);
    boolean status(String user_id, String wish_number);
>>>>>>> yoonsic2
    List<CategoryDto> get_category_detail(Integer wish_number);
    List<TakeoffDto> list(Integer wishNumber);
    boolean update_status(Integer wishNumber, String applyStatus);
    List<TakeoffDto> my_list(String userid);
}
