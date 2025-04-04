package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    boolean join(UserDto userDto);
    String id_check(String user_id);
    String seller_regnum_check(String seller_regnum);
    boolean join_additional_seller(UserDto userDto);
    boolean change_user_type(String user_id, int type);
    String email_check(String user_email);
    UserDto login(String user_id);
    String engineer_regnum_check(String engineerRegnum);
    boolean join_additional_engineer(UserDto userDto);
    String id_find_check(String user_email);
    boolean pw_reset(UserDto userDto);
    String additional(String user_id);
    UserDto get_info(UserDto userDto);
    UserDto get_info_engineer(UserDto userDto);
    boolean update_info(UserDto userDto);
    boolean update_engineer_info(UserDto userDto);
    boolean select_area(String user_id, String seller_area);
    String getSeller_area(String user_id);
    String get_seller_company(String userId);
}
