package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    boolean join(UserDto userDto);


    String id_check(String user_id);
}
