package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.SubDto;
import com._401unauthorized.sheep.dto.WishDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishDao {
    List<WishDto> get_wish_list(String user_id);
    boolean insert_wish(WishDto wishdto);
    boolean insert_major(MajorDto majorDto, int wish_number);
    boolean insert_sub(SubDto subDto, int wish_number);
}
