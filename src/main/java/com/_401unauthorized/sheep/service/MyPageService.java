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




<<<<<<< HEAD
    public UserDto get_info(UserDto userDto) {
        UserDto user = new UserDto();
        switch (userDto.getUser_type()){
            case "0":
              user = userDao.get_info(userDto);
              user.setUser_type("회원");
                break;
            case "1":
                user = userDao.get_info(userDto);
                user.setUser_type("사장님");
                break;
            case "2":
                user =userDao.get_info_engineer(userDto);
                user.setUser_type("능력자님");
                break;
        }
        return user;
=======
    public UserDto getInfo(UserDto userDto) {

        UserDto user = new UserDto();
        log.info("테스트");


        switch (userDto.getUser_type()){
            case "0":
              user = userDao.getInfo(userDto);
              user.setUser_type("회원님");
                log.info("테스트{}", user);
                break;
            case "1":
                user = userDao.getInfo(userDto);
                user.setUser_type("사장님");
                log.info("테스트{}", user);
                break;
            case "2":
                user =userDao.getInfoEngineer(userDto);
                user.setUser_type("기술자님");
                log.info("테스트{}", user);
                break;
        }

        

        return userDto;
>>>>>>> origin/copyminyoung
    }

    public boolean updateInfo(UserDto userDto) {
        boolean result = userDao.updateInfo(userDto);
        log.info("서비스"+userDto.getUser_id());
        if(result){
            return true;
        }else{
            return false;
        }

    }

    public boolean updateEngineerInfo(UserDto userDto) {
        boolean result = userDao.update_engineerinfo(userDto);
        if(result){
            return true;
        }
        return false;
    }
}
