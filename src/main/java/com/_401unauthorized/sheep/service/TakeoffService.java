package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.TakeoffDao;
import com._401unauthorized.sheep.dto.ApplyDto;
<<<<<<< HEAD
import com._401unauthorized.sheep.dto.TakeoffSellerDto;
=======
import com._401unauthorized.sheep.dto.CategoryDto;
import com._401unauthorized.sheep.dto.CategoryListDto;
>>>>>>> eunhye
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
<<<<<<< HEAD
    @Transactional
    public boolean write(List<TakeoffSellerDto> takeoffsellerdto, String user_id, String wish_number) {
        boolean result1 = takeoffDao.status(user_id, wish_number);
            if(!result1){
                return false;
            }
        for(TakeoffSellerDto takeoffsellerDto : takeoffsellerdto){
            boolean result2 = takeoffDao.write(takeoffsellerDto);
            if(!result2){
                return false;
            }
        }
        return true;
=======

    public List<CategoryListDto> category(Integer wish_number) {
        List<CategoryDto> categoryDto = takeoffDao.get_category_detail(wish_number);
        List<CategoryListDto> categoryListDto = new ArrayList<>();
        return categoryListDto;
>>>>>>> eunhye
    }
}
