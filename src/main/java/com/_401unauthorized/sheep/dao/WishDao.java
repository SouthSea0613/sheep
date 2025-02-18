package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.WishDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishDao {
        List<WishDto> get_wish_list(String user_id);
}
