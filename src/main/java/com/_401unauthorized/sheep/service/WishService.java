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
//--------------------------------------------------------------------------------------------
    public List<WishDto> get_wish_list(String user_id) {
        List<WishDto> wish_list = wishDao.get_wish_list(user_id);
        for (WishDto wish : wish_list) {
            if (wish.getApply_status() != null) {
                switch (wish.getApply_status()) {
                    case "0":
                        if(wishDao.get_apply_count(wish.getWish_number())>1){
                            wish.setApply_status("진행중");
                        }
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

//--------------------------------------------------------------------------------------------
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

//--------------------------------------------------------------------------------------------
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

//--------------------------------------------------------------------------------------------
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
        for (CategoryListDto major : categoryListDto) {
            switch (major.getMajor_category()) {
                case "1":
                    major.setMajor_category("철거");
                    break;
                case "2":
                    major.setMajor_category("목공");
                    break;
                case "3":
                    major.setMajor_category("도배");
                    break;
                case "4":
                    major.setMajor_category("전기");
                    break;
                case "5":
                    major.setMajor_category("조명");
                    break;
                case "6":
                    major.setMajor_category("타일");
                    break;
                case "7":
                    major.setMajor_category("필름");
                    break;
                case "8":
                    major.setMajor_category("도장");
                    break;
                case "9":
                    major.setMajor_category("부엌");
                    break;
                case "10":
                    major.setMajor_category("욕실");
                    break;
                case "11":
                    major.setMajor_category("수납");
                    break;
                case "12":
                    major.setMajor_category("바닥");
                    break;
                case "13":
                    major.setMajor_category("창호");
                    break;
                case "14":
                    major.setMajor_category("도어");
                    break;
                case "15":
                    major.setMajor_category("중문");
                    break;
                case "16":
                    major.setMajor_category("공사준비/마감");
                    break;
            }
            if (major.getSub_category().get(0) != null) {
                for (int i = 0; i < major.getSub_category().size(); i++) {
                    switch (major.getSub_category().get(i)) {
                        case "17":
                            major.getSub_category().set(i, "단열");
                            break;
                        case "18":
                            major.getSub_category().set(i, "천장");
                            break;
                        case "19":
                            major.getSub_category().set(i, "벽");
                            break;
                        case "20":
                            major.getSub_category().set(i, "현관방화문");
                            break;
                        case "21":
                            major.getSub_category().set(i, "중문");
                            break;
                        case "22":
                            major.getSub_category().set(i, "천장몰딩");
                            break;
                        case "23":
                            major.getSub_category().set(i, "걸레받이몰딩");
                            break;
                        case "24":
                            major.getSub_category().set(i, "'ㅡ'자");
                            break;
                        case "25":
                            major.getSub_category().set(i, "'ㄱ'자");
                            break;
                        case "26":
                            major.getSub_category().set(i, "대면형");
                            break;
                        case "27":
                            major.getSub_category().set(i, "욕조");
                            break;
                        case "28":
                            major.getSub_category().set(i, "샤워부스");
                            break;
                        case "29":
                            major.getSub_category().set(i, "드레스룸");
                            break;
                        case "30":
                            major.getSub_category().set(i, "붙박이장");
                            break;
                        case "31":
                            major.getSub_category().set(i, "현관장");
                            break;
                        case "32":
                            major.getSub_category().set(i, "장판");
                            break;
                        case "33":
                            major.getSub_category().set(i, "강화마루");
                            break;
                        case "34":
                            major.getSub_category().set(i, "강마루");
                            break;
                        case "35":
                            major.getSub_category().set(i, "타일");
                            break;
                        case "36":
                            major.getSub_category().set(i, "일반창");
                            break;
                        case "37":
                            major.getSub_category().set(i, "터닝도어");
                            break;
                        case "38":
                            major.getSub_category().set(i, "폴딩도어");
                            break;
                        case "39":
                            major.getSub_category().set(i, "3연동");
                            break;
                        case "40":
                            major.getSub_category().set(i, "여닫이");
                            break;
                        case "41":
                            major.getSub_category().set(i, "슬라이딩");
                            break;
                        case "42":
                            major.getSub_category().set(i, "입주민동의서");
                            break;
                        case "43":
                            major.getSub_category().set(i, "EV보양 및 실내외 보양");
                            break;
                        case "44":
                            major.getSub_category().set(i, "입주청소");
                            break;
                    }
                }
            }
        }
        return categoryListDto;
    }
}