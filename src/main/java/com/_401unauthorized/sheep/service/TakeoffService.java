package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.TakeoffDao;
import com._401unauthorized.sheep.dao.UserDao;
import com._401unauthorized.sheep.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.ArrayList;

@Slf4j
@RequiredArgsConstructor
@Service
public class TakeoffService {
    private final TakeoffDao takeoffDao;
    private final UserDao userDao;

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

    public WishDto essential(Integer wish_number, String user_id) {
        WishDto takeoffDto = takeoffDao.get_wish_detail(wish_number,user_id);
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

        if (takeoffDto.getApply_status() != null) {
            switch (takeoffDto.getApply_status()) {
                case "0":
                    takeoffDto.setApply_status("대기중");
                    break;

                case "1":
                    takeoffDto.setApply_status("상담중");
                    break;

                case "2":
                    takeoffDto.setApply_status("계약완료");
                    break;

                case "3":
                    takeoffDto.setApply_status("취소");
                    break;

                case "4":
                    takeoffDto.setApply_status("기간만료");
                    break;
            }
        }
        log.info("######현재 상태는? "+takeoffDto.getApply_status());
        return takeoffDto;
    }

    public List<CategoryListDto> takeoff(Integer wish_number, String user_id) {
        List<CategoryDto> takeoffCategoryDto = takeoffDao.get_category_detail(wish_number, user_id);
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
                log.info("#####서브란? "+takeoffSellerDto.get(index).getSub_category());
            } else {
                List<String> takeoffCategoryList = new ArrayList<>();
                takeoffCategoryList.add(takeCategory.getCategory_number());
                CategoryListDto takeoff_list_category = new CategoryListDto();
                takeoff_list_category.setMajor_category(takeCategory.getCategory_parent());
                takeoff_list_category.setMajor_text(takeCategory.getMajor_text());
                takeoff_list_category.setSub_category(takeoffCategoryList);
                takeoff_list_category.setWish_category_seller_answer((takeCategory.getWish_category_seller_answer()));
                takeoff_list_category.setWish_category_seller_price(takeCategory.getWish_category_seller_price());
                takeoffSellerDto.add(takeoff_list_category);
            }
        }

        for (CategoryListDto major : takeoffSellerDto) {
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
                            major.getSub_category().set(i, "중문필름");
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
        return takeoffSellerDto;
    }

    public List<TakeoffDto> list(Integer wishNumber) {
      List<TakeoffDto> takeoffdtolist = takeoffDao.list(wishNumber);
      log.info(takeoffdtolist.toString());
      for(int i=0; i<takeoffdtolist.size();i++){
        switch(takeoffdtolist.get(i).getApply_status()){
            case "0" :
                takeoffdtolist.get(i).setApply_status("대기중");
                break;
            case "1" :
                takeoffdtolist.get(i).setApply_status("상담중");
                break;
            case "2":
                takeoffdtolist.get(i).setApply_status("계약완료");
                break;
            case "3":
                takeoffdtolist.get(i).setApply_status("취소");
                break;
            case "4":
                takeoffdtolist.get(i).setApply_status("기간만료");
                break;
        }
      }
        return takeoffdtolist;
    }

    public List<TakeoffDto> my_list(String userid) {
        List<TakeoffDto> takeoffdtolist =takeoffDao.my_list(userid);
        for(TakeoffDto takeoffdto : takeoffdtolist){
            switch (takeoffdto.getApply_status()){
                case "0" :
                    takeoffdto.setApply_status("대기중");
                    break;
                case "1":
                    takeoffdto.setApply_status("상담중");
                    break;
                case "2":
                    takeoffdto.setApply_status("계약완료");
                    break;
                case "3":
                    takeoffdto.setApply_status("취소");
                    break;
                case "4":
                    takeoffdto.setApply_status("기간만료");
                    break;
                    
            }
        }

        return takeoffdtolist;
    }

    @Transactional
    public boolean write(List<TakeoffSellerDto> takeoffsellerdto, String user_id, Integer wish_number) {
//        Integer result3 = takeoffDao.counttakeoff(wish_number);
//        if(result3<6 || result3==null){
        boolean result1 = takeoffDao.status(user_id, wish_number);
        if (!result1) {
            return false;
        }
        for (TakeoffSellerDto takeoffsellerDto : takeoffsellerdto) {
            boolean result2 = takeoffDao.write(takeoffsellerDto);
            if (!result2) {
                return false;
            }
        }
//        }
        return true;
    }

//    @Transactional
//    public boolean complete(Integer wish_number, String user_id, String seller_user_id) {
//        if (takeoffDao.complete(wish_number, user_id, seller_user_id)) {
//            return true;
//        }
//        return false;
//    }

    public boolean count(Integer wish_number,String user_id) {
        if(takeoffDao.count(wish_number,user_id)){
            return true;
        }
        return false;
    }

    public boolean checkarea(String user_id) {
        if(takeoffDao.checkarea(user_id)){
            return true;
        }
        return false;
    }

    public boolean select_area(String user_id, String seller_area) {
        return userDao.select_area(user_id, seller_area);
    }

    @Transactional
    public boolean complete(Integer wish_number, String user_id, String session_user_id) {
        if(!takeoffDao.contract(wish_number,user_id,session_user_id)){
            return false;
        }
        if(!takeoffDao.update_contract_status(wish_number)){
            return false;
        }

        return true;
    }

    public boolean changestatus(Integer wish_number, String user_id) {
        if(takeoffDao.changestatus(wish_number,user_id)){
            return true;
        }
        return false;
    }

    @Transactional
    public boolean confirm(Integer wish_number, String user_id) {
        if(!takeoffDao.changestatus(wish_number,user_id)){
            return false;
        }
        if(!takeoffDao.change_end_status(wish_number)){
            return false;
        }
        return true;
    }

    public WishDto getstatus(String user_id, int wish_number) {
        return takeoffDao.getstatus(user_id,wish_number);
    }

    public WishDto endwish(String user_id) {
        WishDto wishdto = takeoffDao.endwish(user_id);
        if(wishdto!=null){
            switch (wishdto.getApply_status()){
                case "0", "1":
                    wishdto.setApply_status("진행중");
                    break;
                case "2":
                    wishdto.setApply_status("계약완료");
                    break;
                case "3":
                    wishdto.setApply_status("취소");
                    break;
                case "4":
                    wishdto.setApply_status("기간만료");
            }
        }
        return wishdto;
    }

    public List<TakeoffDto> endtakeoff(String user_id, Integer wish_number) {
        List<TakeoffDto> takeoffdtoList = takeoffDao.endtakeoff(user_id,wish_number);
        for(TakeoffDto takeoffdto : takeoffdtoList){
            if(takeoffdto.getApply_status()!=null){
                switch (takeoffdto.getApply_status()){
                    case "2":
                        takeoffdto.setApply_status("계약완료");
                        break;
                    case "3":
                        takeoffdto.setApply_status("취소");
                    case "4":
                        takeoffdto.setApply_status("기간만료");
                        break;
                }
            }
        }
        return takeoffdtoList;
    }

    public Integer get_takeoff(String user_id, Integer wish_number) {
        return takeoffDao.get_takeoff(user_id,wish_number);
    }

    public List<TakeoffDto> endseller_takeoff(String user_id) {
        List<TakeoffDto> takeoffdtoList = takeoffDao.endseller_takeoff(user_id);
        if(takeoffdtoList!=null){
            for(TakeoffDto takeoffdto : takeoffdtoList){
                switch (takeoffdto.getApply_status()){
                    case "2":
                        takeoffdto.setApply_status("계약완료");
                        break;
                    case "3":
                        takeoffdto.setApply_status("취소");
                    case "4":
                        takeoffdto.setApply_status("기간만료");
                        break;
                }
            }
        }
        return takeoffdtoList;
    }

    public List<TakeoffDto> get_seller_status(Integer wish_number, String user_id) {
        return takeoffDao.get_seller_status(wish_number,user_id);
    }
}
