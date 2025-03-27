package com._401unauthorized.sheep.dao;

import com._401unauthorized.sheep.dto.BoardDto;
import com._401unauthorized.sheep.dto.FileDto;
import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.WishDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerDao {
    List<WishDto> seller_list(String user_id);
    List<MajorDto> get_category(int wish_number);
    BoardDto seller_main(String user_id);
    boolean seller_write_main(BoardDto boardDto);
    boolean board_file_insert(FileDto fileDto);
}
