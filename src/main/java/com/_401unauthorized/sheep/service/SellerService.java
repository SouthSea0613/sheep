package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.SellerDao;
import com._401unauthorized.sheep.dao.UserDao;
import com._401unauthorized.sheep.dto.BoardDto;
import com._401unauthorized.sheep.dto.FileDto;
import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.WishDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerDao sellerDao;
    private final UserDao userDao;

    public List<WishDto> seller_list(String user_id) {
        List<WishDto> sellerlist = sellerDao.seller_list(user_id);
        for (int i = 0; i < sellerlist.size(); i++) {
            List<MajorDto> major_category = sellerDao.get_category(sellerlist.get(i).getWish_number());
            for (MajorDto major : major_category) {
                switch (major.getCategory_number()) {
                    case "1":
                        major.setCategory_number("철거");
                        break;
                    case "2":
                        major.setCategory_number("목공");
                        break;
                    case "3":
                        major.setCategory_number("도배");
                        break;
                    case "4":
                        major.setCategory_number("전기");
                        break;
                    case "5":
                        major.setCategory_number("조명");
                        break;
                    case "6":
                        major.setCategory_number("타일");
                        break;
                    case "7":
                        major.setCategory_number("필름");
                        break;
                    case "8":
                        major.setCategory_number("도장");
                        break;
                    case "9":
                        major.setCategory_number("부엌");
                        break;
                    case "10":
                        major.setCategory_number("욕실");
                        break;
                    case "11":
                        major.setCategory_number("수납");
                        break;
                    case "12":
                        major.setCategory_number("바닥");
                        break;
                    case "13":
                        major.setCategory_number("창호");
                        break;
                    case "14":
                        major.setCategory_number("도어");
                        break;
                    case "15":
                        major.setCategory_number("중문");
                        break;
                    case "16":
                        major.setCategory_number("공사준비/마감");
                        break;
                }
            }
            sellerlist.get(i).setMajor_category(major_category);
        }
        return sellerlist;
    }

    public BoardDto seller_main(String user_id) {
        return sellerDao.seller_main(user_id);
    }

    @Transactional
    public boolean seller_write_main(BoardDto board_dto, String real_path) {
        if (sellerDao.seller_write_main(board_dto)) {
            if (!board_dto.getMultipart_files().get(0).isEmpty()) {
                for (MultipartFile multipartFile : board_dto.getMultipart_files()) {
                    String pf_systemname = UUID.randomUUID() + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());

                    try {
                        multipartFile.transferTo(new File(real_path + pf_systemname));
                        if (!sellerDao.board_file_insert(new FileDto(pf_systemname, board_dto.getBoard_number()))) {
                            return false;
                        }
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            return true;
        }
        else {
            return false;
        }
    }
}
