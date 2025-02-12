package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.MyPageDao;
import com._401unauthorized.sheep.dao.UserDao;
import com._401unauthorized.sheep.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {
    private final MyPageDao myPageDao;
    private final UserDao userDao;

    public boolean additional(UserDto userDto) {
        String user_pw = userDao.additional(userDto.getUser_id());
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            if (pwEncoder.matches(userDto.getUser_pw(), user_pw)) {
                return true;
            } else {
                return false;
            }
        }
    }
