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
                        wish.setApply_status("대기중");
                        break;

                    case "1":
                        wish.setApply_status("상담중");
                        break;

                    case "2":
                        wish.setApply_status("계약완료");
                        break;

                    case "3":
                        wish.setApply_status("기간만료");
                        break;

                    case "4":
                        wish.setApply_status("취소");
                        break;
                }
            } else {
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

        if (!wishDao.insert_wish(wishdto)) {
            return false;
        }
        for (MajorDto majorDto : wishDto.getMajor_category()) {
            majorDto.setWish_number(wishdto.getWish_number());
            if (!wishDao.insert_major(majorDto)) {
                return false;
            }
        }
        for (SubDto subDto : wishDto.getSub_category()) {
            subDto.setWish_number(wishdto.getWish_number());
            if (!wishDao.insert_sub(subDto)) {
                return false;
            }
        }
        return true;
    }

    public WishDto essential(Integer wish_number) {
        WishDto wishDto = wishDao.get_wish_detail(wish_number);
            if (wishDto.getWish_type() != null) {
                switch (wishDto.getWish_type()) {
                    case "1":
                        wishDto.setWish_type("아파트");
                        break;

                    case "2":
                        wishDto.setWish_type("단독주택");
                        break;

                    case "3":
                        wishDto.setWish_type("빌라");
                        break;
                }
            }
        return wishDto;
    }

    public List<CategoryListDto> category(Integer wish_number) {
        List<CategoryDto> categoryDto = wishDao.get_category_detail(wish_number);
        List<CategoryListDto> categoryListDto = new ArrayList<>();

        for (CategoryDto category : categoryDto) {
            boolean check = false;
            int index = 0;
            if (!categoryListDto.isEmpty()) {
                for (int i = 0; i < categoryListDto.size(); i++) {
                    if (categoryListDto.get(i).getMajor_category().equals(category.getCategory_parent())) {
                        check = true;
                        index = i;
                        break;
                    }
                }
            }

            if (category.getCategory_parent() != null) {
                switch (category.getCategory_parent()) {
                    case "1":
                        category.setCategory_parent("철거");
                        break;

                    case "2":
                        category.setCategory_parent("목공");
                        break;

                    case "3":
                        category.setCategory_parent("도배");
                        break;

                    case "4":
                        category.setCategory_parent("전기");
                        break;

                    case "5":
                        category.setCategory_parent("조명");
                        break;

                    case "6":
                        category.setCategory_parent("타일");
                        break;

                    case "7":
                        category.setCategory_parent("필름");
                        break;

                    case "8":
                        category.setCategory_parent("도장");
                        break;

                    case "9":
                        category.setCategory_parent("부엌");
                        break;

                    case "10":
                        category.setCategory_parent("욕실");
                        break;

                    case "11":
                        category.setCategory_parent("수납");
                        break;

                    case "12":
                        category.setCategory_parent("바닥");
                        break;

                    case "13":
                        category.setCategory_parent("창호");
                        break;

                    case "14":
                        category.setCategory_parent("도어");
                        break;

                    case "15":
                        category.setCategory_parent("중문");
                        break;

                    case "16":
                        category.setCategory_parent("공사준비/마감");
                        break;
                }
            }

            if (category.getCategory_number() != null) {
                switch (category.getCategory_number()) {
                    case "17":
                        category.setCategory_number("단열");
                        break;

                    case "18":
                        category.setCategory_number("천장");
                        break;

                    case "19":
                        category.setCategory_number("벽");
                        break;

                    case "20":
                        category.setCategory_number("현관방화문");
                        break;

                    case "21":
                        category.setCategory_number("중문");
                        break;

                    case "22":
                        category.setCategory_number("천장몰딩");
                        break;

                    case "23":
                        category.setCategory_number("걸레받이몰딩");
                        break;

                    case "24":
                        category.setCategory_number("'-'자");
                        break;

                    case "25":
                        category.setCategory_number("'ㄱ'자");
                        break;

                    case "26":
                        category.setCategory_number("대면형");
                        break;

                    case "27":
                        category.setCategory_number("욕조");
                        break;

                    case "28":
                        category.setCategory_number("샤워부스");
                        break;

                    case "29":
                        category.setCategory_number("드레스룸");
                        break;

                    case "30":
                        category.setCategory_number("붙박이장");
                        break;

                    case "31":
                        category.setCategory_number("현관장");
                        break;

                    case "32":
                        category.setCategory_number("장판");
                        break;

                    case "33":
                        category.setCategory_number("강화마루");
                        break;

                    case "34":
                        category.setCategory_number("강마루");
                        break;

                    case "35":
                        category.setCategory_number("타일");
                        break;

                    case "36":
                        category.setCategory_number("일반창");
                        break;

                    case "37":
                        category.setCategory_number("터닝도어");
                        break;

                    case "38":
                        category.setCategory_number("폴딩도어");
                        break;

                    case "39":
                        category.setCategory_number("3연동");
                        break;

                    case "40":
                        category.setCategory_number("여닫이");
                        break;

                    case "41":
                        category.setCategory_number("슬라이딩");
                        break;

                    case "42":
                        category.setCategory_number("입주민동의서");
                        break;

                    case "43":
                        category.setCategory_number("EV보양 및 실내외 보양");
                        break;

                    case "44":
                        category.setCategory_number("입주청소");
                        break;
                }
            }

            if (check) {
                categoryListDto.get(index).getSub_category().add(category.getCategory_number());
            }else{
                List<String> categoryList = new ArrayList<>();
                categoryList.add(category.getCategory_number());

                CategoryListDto category_list_dto = new CategoryListDto();
                category_list_dto.setMajor_category(category.getCategory_parent());
                category_list_dto.setMajor_text(category.getMajor_text());
                category_list_dto.setSub_category(categoryList);

                categoryListDto.add(category_list_dto);
            }
        }

        return categoryListDto;
    }
}