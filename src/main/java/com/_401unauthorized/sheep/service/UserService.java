package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.UserDao;
import com._401unauthorized.sheep.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserDao userDao;

    public boolean service_join(UserDto userDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encoder_pw = encoder.encode(userDto.getUser_pw());
        userDto.setUser_pw(encoder_pw);
        return userDao.join(userDto);
    }

    public boolean id_check(String user_id) {
        if (userDao.id_check(user_id) != null) {
            return true;
        }
        return false;
    }

    public boolean email_check(String user_email) {
        if (userDao.email_check(user_email) != null) {
            return true;
        }
        return false;
    }

    public boolean login(UserDto userDto) {
        UserDto user = userDao.login(userDto.getUser_id());
        if (user != null) {
            BCryptPasswordEncoder pwEncoder = new BCryptPasswordEncoder();
            if (pwEncoder.matches(userDto.getUser_pw(), user.getUser_pw())) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

}

