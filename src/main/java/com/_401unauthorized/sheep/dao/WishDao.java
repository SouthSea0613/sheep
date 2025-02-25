package com._401unauthorized.sheep.dao;

<<<<<<< HEAD
import com._401unauthorized.sheep.dto.CategoryDto;
import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.SubDto;
import com._401unauthorized.sheep.dto.WishDto;
=======
import com._401unauthorized.sheep.dto.*;
>>>>>>> minyoung
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishDao {
    List<WishDto> get_wish_list(String user_id);
    boolean insert_wish(WishDto wishdto);
    boolean insert_major(MajorDto majorDto);
    boolean insert_sub(SubDto subDto);
<<<<<<< HEAD

    WishDto detail(Integer wish_number);
    List<CategoryDto> category_detail(Integer wish_number);

=======
    WishDto get_wish_detail(int wish_number);
    List<CategoryDto> get_category_detail(int wish_number);
>>>>>>> minyoung
}
