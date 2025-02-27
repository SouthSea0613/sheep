package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.TakeoffDao;
import com._401unauthorized.sheep.dto.*;
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
        if (result) {
            log.info("서비스 true");
            return true;
        } else {
            log.info("서비스 false");
            return false;
        }
    }

    public WishDto essential(Integer wish_number) {
        WishDto takeoffDto = takeoffDao.get_wish_detail(wish_number);
        if (takeoffDto.getWish_type() != null) {
            switch (takeoffDto.getWish_type()) {
                case "1":
                    takeoffDto.setWish_type("아파트");
                    break;

                case "2":
                    takeoffDto.setWish_type("단독주택");
                    break;

                case "3":
                    takeoffDto.setWish_type("빌라");
                    break;
            }
        }
        return takeoffDto;
    }

    public List<CategoryListDto> takeoff(Integer wish_number) {
        List<CategoryDto> takeoffCategoryDto = takeoffDao.get_category_detail(wish_number);
        List<CategoryListDto> takeoffSellerDto = new ArrayList<>();

        for (CategoryDto takeCategory : takeoffCategoryDto) {
            boolean check = false;
            int index = 0;
            if (!takeoffSellerDto.isEmpty()) {
                for (int i = 0; i < takeoffSellerDto.size(); i++) {
                    if (takeoffSellerDto.get(i).getMajor_category().equals(takeCategory.getCategory_parent())) {
                        check = true;
                        index = i;
                        break;
                    }
                }
            }
            if (check) {
                takeoffSellerDto.get(index).getSub_category().add(takeCategory.getCategory_number());
            } else {
                List<String> takeoffCategoryList = new ArrayList<>();
                takeoffCategoryList.add(takeCategory.getCategory_number());

                CategoryListDto takeoff_list_category = new CategoryListDto();
                takeoff_list_category.setMajor_category(takeCategory.getCategory_parent());
                takeoff_list_category.setMajor_text(takeCategory.getMajor_text());
                takeoff_list_category.setSub_category(takeoffCategoryList);

                takeoffSellerDto.add(takeoff_list_category);
            }
        }

        for (CategoryListDto major : takeoffSellerDto) {
            switch (major.getMajor_category()) {
                case "1":
                    major.setMajor_category("철거");
            }
        }
        return takeoffSellerDto;
    }
}
