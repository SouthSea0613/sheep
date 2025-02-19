package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.SubDto;
import com._401unauthorized.sheep.dto.WishDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishDao {
<<<<<<< HEAD
    List<WishDto> get_wish_list(String user_id);
    // 위시 작성 insert
    boolean insert_wish(WishDto wishdto);
    boolean insert_major(MajorDto majorDto, int wish_number);
    boolean insert_sub(SubDto subDto, int wish_number);
=======
        List<WishDto> get_wish_list(String user_id);

        boolean insert_wish(WishDto wishDto);

        boolean insert_major(MajorDto majorDto, int wish_number);

        boolean insert_sub(SubDto subDto, int wish_number);
>>>>>>> user_eunhye
}
