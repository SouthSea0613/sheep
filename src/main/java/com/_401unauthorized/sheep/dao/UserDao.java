package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.EngineerDto;
import com._401unauthorized.sheep.dto.SellerDto;
import com._401unauthorized.sheep.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    boolean join(UserDto userDto);
    String id_check(String user_id);
    String seller_regnum_check(String seller_regnum);
    boolean join_additional_seller(SellerDto sellerDto);
    boolean change_user_type(String user_id, int type);
    String email_check(String user_email);
    UserDto login(String user_id);
<<<<<<< HEAD
    String engineer_regnum_check(String engineerRegnum);
    boolean join_additional_engineer(EngineerDto engineerDto);
=======
    String id_find_check(String user_email);
>>>>>>> origin/copyuser_jieun
}
