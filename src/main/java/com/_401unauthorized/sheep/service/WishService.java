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

    public List<WishDto> get_wish_list(String user_id) {
        List<WishDto> wish_list = wishDao.get_wish_list(user_id);
        for (WishDto wish : wish_list) {
            if (wish.getApply_status() != null) {
                // 여기 있는 switch-case는 명칭 정해지면 다시 디벨롭해야해
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
//                wish.setApply_status("");
                wish.setApply_status(null);
            }
        }
        return wish_list;
    }

    @Transactional
    public boolean write(WishDto wishDto) {
        wishDao.insert_wish(wishDto);
        for(MajorDto majorDto : List<MajorDto> major_list) {
            wishDao.insert_major(majorDto);
        }
        return true;

        for(SubDto subDto: List<SubDto> sub_list) {
            wishDao.insert_sub(subDto);
        }
        return true;
    }
}
