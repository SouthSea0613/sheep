package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.WishDao;
import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.SubDto;
import com._401unauthorized.sheep.dto.WishDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WishServiceTest {
    @Autowired
    private WishService wishService;

    @Test
    public void write(WishDto wishDto) {
        WishDao wishDao = null;
        wishDao.insert_wish(wishDto);
        for (MajorDto majorDto : wishDto.getMajor_category()) {
            wishDao.insert_major(majorDto);
        }

        for (SubDto subDto : wishDto.getSub_category()) {
            wishDao.insert_sub(subDto);
        }
        return true;
    }

}
