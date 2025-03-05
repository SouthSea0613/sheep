package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.TakeoffDao;
import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.dto.CategoryDto;
import com._401unauthorized.sheep.dto.CategoryListDto;
import com._401unauthorized.sheep.dto.TakeoffDto;
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

    public List<TakeoffDto> list(Integer wishNumber) {
      List<TakeoffDto> takeoffdtolist = takeoffDao.list(wishNumber);
        switch(takeoffdtolist.get(1).getApply_status()){
            case "1" :
//                takeoffdtolist.set(7,"상담중");
                break;
            case "2" :
                break;
            
                
        }

        return takeoffDao.list(wishNumber);
    }

    public boolean update_status(Integer wishNumber, String applyStatus) {
        return takeoffDao.update_status(wishNumber,applyStatus);
    }

    public List<TakeoffDto> my_list(String userid) {
        return takeoffDao.my_list(userid);
    }
}
