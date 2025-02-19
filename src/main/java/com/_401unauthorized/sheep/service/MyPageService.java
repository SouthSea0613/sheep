package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.UserDao;
import com._401unauthorized.sheep.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {
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

    public UserDto get_info(UserDto userDto) {
        UserDto user = new UserDto();
        switch (userDto.getUser_type()) {
            case "0":
                user = userDao.get_info(userDto);
                user.setUser_type("회원");
                break;
            case "1":
                user = userDao.get_info(userDto);
                user.setUser_type("사장님");
                break;
            case "2":
                user = userDao.get_info_engineer(userDto);
                user.setUser_type("능력자님");
                break;
        }
        return user;
    }

    @Transactional
    public boolean update_info(UserDto userDto) {
        if(userDao.update_info(userDto)){
            if(userDto.getUser_type().equals("2")){
                return userDao.update_engineer_info(userDto);
            }
            return true;
        }
        return false;
    }
}

