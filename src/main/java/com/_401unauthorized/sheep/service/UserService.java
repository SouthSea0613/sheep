package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.UserDao;
import com._401unauthorized.sheep.dto.EngineerDto;
import com._401unauthorized.sheep.dto.SellerDto;
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

    public boolean join(UserDto userDto) {
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
        else {
            return false;
        }
    }

    public boolean seller_regnum_check(String seller_regnum) {
        if(userDao.seller_regnum_check(seller_regnum) != null){
            return true;
        }
        return false;
    }

    public boolean join_additional_seller(SellerDto sellerDto) {
        return userDao.change_user_type(sellerDto.getUser_id(), 1) && userDao.join_additional_seller(sellerDto);
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

<<<<<<< HEAD
    public boolean engineer_regnum_check(String engineer_regnum) {
        if(userDao.engineer_regnum_check(engineer_regnum) != null) {
=======
    public boolean id_find_check(String user_email) {
        if (userDao.id_find_check(user_email) != null) {
>>>>>>> origin/copyuser_jieun
            return true;
        }
        return false;
    }
<<<<<<< HEAD

    public boolean join_additional_engineer(EngineerDto engineerDto) {
        return userDao.change_user_type(engineerDto.getUser_id(),2) && userDao.join_additional_engineer(engineerDto);
    }
=======
>>>>>>> origin/copyuser_jieun
}

