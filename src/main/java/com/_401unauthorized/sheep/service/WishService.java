package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.WishDao;
import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.SubDto;
import com._401unauthorized.sheep.dto.WishDto;
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

//    리스트 출력
    public List<WishDto> get_wish_list(String user_id) {
        List<WishDto> wish_list = wishDao.get_wish_list(user_id);
        for (WishDto wish : wish_list) {
            if (wish.getApply_status() != null) {
//                명칭이랑 스테이터스 값 정한 뒤 수정
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

//    위시 작성
    @Transactional
    public boolean write(WishDto wishDto) {
//        위시 필수 빈깡통 만들기
        WishDto wishdto = new WishDto();

//        위시 필수 빈깡통에 데이터 넣기
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
//        포문 돌려서 메이저 서브 데이터 리스트 넣기.
        for(MajorDto majorDto :wishDto.getMajor_category()) {
            if(!wishDao.insert_major(majorDto, wishdto.getWish_number())) {
                return false;
            }
        }
        for(SubDto subDto :wishDto.getSub_category()) {
           if(!wishDao.insert_sub(subDto, wishdto.getWish_number())) {
               return false;
           }
        }
        return true;
    }
}