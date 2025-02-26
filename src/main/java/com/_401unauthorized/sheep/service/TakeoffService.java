package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.TakeoffDao;
import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.dto.CategoryDto;
import com._401unauthorized.sheep.dto.CategoryListDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class TakeoffService {
    private final TakeoffDao takeoffDao;

    public boolean call(ApplyDto applyDto) {
      boolean result = takeoffDao.call(applyDto);
        if(result){
            log.info("서비스 true");
            return true;
        }else{
            log.info("서비스 false");
            return false;
        }
    }

    public List<CategoryListDto> category(Integer wish_number) {
        List<CategoryDto> categoryDto = takeoffDao.get_category_detail(wish_number);
        List<CategoryListDto> categoryListDto = new ArrayList<>();
        return categoryListDto;
    }
}
