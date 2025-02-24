package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.WishDao;
import com._401unauthorized.sheep.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WishService {
    private final WishDao wishDao;

    public List<WishDto> get_wish_list(String user_id) {
        List<WishDto> wish_list = wishDao.get_wish_list(user_id);
        for (WishDto wish : wish_list) {
            if (wish.getApply_status() != null) {
                switch (wish.getApply_status()) {
                    case "0":
                        wish.setApply_status("취소");
                        break;

                    case "1":
                        wish.setApply_status("기간만료");
                        break;

                    case "2":
                        wish.setApply_status("견적신청중");
                        break;
                }
            }
            else {
                wish.setApply_status(null);
            }
        }
        return wish_list;
    }

    @Transactional
    public boolean write(WishDto wishDto) {
        log.info(wishDto.toString());
        // 컨트롤러에서 넘어온 wishDto는 major_category, sub_category까지 전부 들어있기 때문에 너무 무거워!!!!
        // 필수요소를 빼서 좀 더 가볍게 넣어 필수항목 먼저 인서트! 필수요소 다음 카테고리들이 있을 경우 순차 진행
        // 진행되는 동안 에러 날 경우 Transactional로 진행하던 데이터 인서트 중지
        WishDto wishdto = new WishDto();
        wishdto.setUser_id(wishDto.getUser_id());
        wishdto.setWish_title(wishDto.getWish_title());
        wishdto.setWish_type(wishDto.getWish_type());
        wishdto.setWish_size(wishDto.getWish_size());
        wishdto.setWish_size_text(wishDto.getWish_size_text());
        wishdto.setWish_money(wishDto.getWish_money());
        wishdto.setWish_addr(wishDto.getWish_addr());

        if(!wishDao.insert_wish(wishdto)) {
            return false;
        }

        log.info(wishDto.toString());

        for (MajorDto majorDto : wishDto.getMajor_category()) {
            majorDto.setWish_number(wishdto.getWish_number());
            if(!wishDao.insert_major(majorDto)) {
                return false;
            }
        }

        for (SubDto subDto : wishDto.getSub_category()) {
            subDto.setWish_number(wishdto.getWish_number());
            if(!wishDao.insert_sub(subDto)) {
                return false;
            }
        }
        return true;
    }
    public List<CategoryListDto> category(Integer wish_number){
        List<CategoryDto> categoryDto =wishDao.category_detail(wish_number);
        List<CategoryListDto> categoryListDto = new ArrayList<>();

        for(CategoryDto category : categoryDto){
            boolean check = false;
            int tmp = 0;
            if (!categoryListDto.isEmpty()) {
                for (int i = 0; i < categoryListDto.size(); i++) {
                    if (categoryListDto.get(i).getMajor_category().equals(category.getMajor_category())) {
                        check = true;
                        tmp = i;
                        break;
                    }
                }
            }
            else {
                List<String> ttmmpp = new ArrayList<String>();
                ttmmpp.add(category.getSub_category());
                CategoryListDto tttmmmppp = new CategoryListDto(
                        category.getMajor_category(),
                        category.getMajor_text(),
                        ttmmpp);
                categoryListDto.add(tttmmmppp);
            }

            if (check) {
                categoryListDto.get(tmp).getSub_category().add(category.getSub_category());
            }else{
                List<String> ttmmpp = new ArrayList<String>();
                ttmmpp.add(category.getSub_category());
                CategoryListDto tttmmmppp = new CategoryListDto(
                        category.getMajor_category(),
                        category.getMajor_text(),
                        ttmmpp);
                categoryListDto.add(tttmmmppp);
            }
        }

        return categoryListDto;
    }
    public WishDto essential(Integer wish_number){
        return wishDao.detail(wish_number);
    }
}
