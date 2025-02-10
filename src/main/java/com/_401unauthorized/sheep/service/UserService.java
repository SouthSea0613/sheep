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
        return  userDao.join(userDto);
    }

    public boolean id_check(String user_id) {
        if(userDao.id_check(user_id) != null){
            return true;
        }
        return false;
    }
}
