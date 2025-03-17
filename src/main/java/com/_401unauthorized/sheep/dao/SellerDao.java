package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.WishDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerDao {
    List<WishDto> seller_list(String user_id);
    List<MajorDto> get_category(int wish_number);
}
