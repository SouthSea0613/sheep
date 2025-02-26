package com._401unauthorized.sheep.dao;

<<<<<<< HEAD
import com._401unauthorized.sheep.dto.*;
=======
import com._401unauthorized.sheep.dto.ApplyDto;
>>>>>>> jieun
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TakeoffDao {
<<<<<<< HEAD
    boolean call(ApplyDto applyDto);
=======
    void call(ApplyDto applyDto);
>>>>>>> jieun
}
