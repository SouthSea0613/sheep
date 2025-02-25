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

<<<<<<< HEAD

    public List<CategoryListDto> category(Integer wish_number){
        List<CategoryDto> categorydto =wishDao.category_detail(wish_number);
        List<CategoryListDto> categorylistdto = new ArrayList<>();

        for(CategoryDto category : categorydto){
            boolean check = false;
            int index = 0;
            if (!categorylistdto.isEmpty()) {
                for (int i = 0; i < categorylistdto.size(); i++) {
                    if (categorylistdto.get(i).getMajor_category().equals(category.getCategory_parent())) {
                        check = true;
=======
    //필수
    public WishDto get_wish_detail(int wish_number){
        return wishDao.get_wish_detail(wish_number);
    }
    //대 , 텍스트, 중
    public List<CategoryListDto> get_category_detail(int wish_number){
        List<CategoryDto> categorydto = wishDao.get_category_detail(wish_number);
        List<CategoryListDto> categorylistdto = new ArrayList<>();

        for (CategoryDto categoryDto : categorydto) {
            boolean ckeck = false;
            int index = 0;
            if(!categorylistdto.isEmpty()){
                for(int i=0; i<categorylistdto.size(); i++){
                    if(categorylistdto.get(i).getMajor_category().equals(categoryDto.getCategory_number())){
                        ckeck = true;
>>>>>>> yoonsic2
                        index = i;
                        break;
                    }
                }
            }
<<<<<<< HEAD

            if (check) {
                categorylistdto.get(index).getSub_category().add(category.getCategory_number());
            }else{
                List<String> categoryList = new ArrayList<String>();
                categoryList.add(category.getCategory_number());

                CategoryListDto category_list_dto = new CategoryListDto();
                category_list_dto.setMajor_category(category.getCategory_parent());
                category_list_dto.setMajor_text(category.getMajor_text());
                category_list_dto.setSub_category(categoryList);
=======
            if(ckeck){
                categorylistdto.get(index).getSub_category().add(categoryDto.getCategory_number());
            } else {
                List<String> categorylist = new ArrayList<>();
                categorylist.add(categoryDto.getCategory_number());

                CategoryListDto category_list_dto = new CategoryListDto();
                category_list_dto.setMajor_category(categoryDto.getCategory_parent());
                category_list_dto.setMajor_text(categoryDto.getMajor_text());
                category_list_dto.setSub_category(categorylist);
>>>>>>> yoonsic2

                categorylistdto.add(category_list_dto);
            }
        }


<<<<<<< HEAD
        return categorylistdto;
    }

    public WishDto essential(Integer wish_number){
        return wishDao.detail(wish_number);
    }
=======
        return null;
    }



>>>>>>> yoonsic2
}
