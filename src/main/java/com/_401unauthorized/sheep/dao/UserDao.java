package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.SellerDto;
import com._401unauthorized.sheep.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    boolean join(UserDto userDto);

    String id_check(String user_id);
<<<<<<< HEAD

    String email_check(String user_email);

    String seller_regnum_check(String seller_regnum);

    boolean join_additional_seller(SellerDto sellerDto);

    boolean change_user_type(String user_id, int type);
=======
    String email_check(String user_email);
    String getSecurityPw(String user_id);
    UserDto getUserInfo(String user_id);
    UserDto login(String user_id);
>>>>>>> copyuser_jieun
}
