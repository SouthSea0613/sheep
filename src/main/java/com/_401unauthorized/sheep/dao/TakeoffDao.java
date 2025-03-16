package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TakeoffDao {
    boolean call(ApplyDto applyDto);
    WishDto get_wish_detail(Integer wish_number, String user_id);
    boolean write(TakeoffSellerDto takeoffsellerDto);
    boolean status(String user_id, Integer wish_number);
    List<CategoryDto> get_category_detail(Integer wish_number, String user_id);
    List<TakeoffDto> list(Integer wish_number);
    List<TakeoffDto> my_list(String userid);
    boolean complete(Integer wish_number, String user_id);
    Integer counttakeoff(Integer wish_number);
    boolean update_status(Integer wish_number, String user_id);

    boolean count(Integer wish_number, String user_id);

    boolean checkarea(String userId);

    boolean contract(Integer wish_number, String user_id, String session_user_id);

    boolean changestatus(Integer wish_number, String user_id);

    WishDto getstatus(String user_id, int wish_number);

    boolean update_contract_status(Integer wish_number, String user_id, String session_user_id);

    boolean update_wish_status(Integer wish_number, String user_id);

    WishDto endwish(String user_id);

    List<TakeoffDto> endtakeoff(String user_id, Integer wish_number);

    Integer get_takeoff(String user_id, Integer wish_number);

    List<TakeoffDto> endseller_takeoff(String user_id);
}
