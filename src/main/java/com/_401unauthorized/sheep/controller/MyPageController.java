package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.UserDto;
import com._401unauthorized.sheep.service.MyPageService;
import com._401unauthorized.sheep.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/mypage")
@Controller
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping("/additional")
    public String additional() {
        return "mypage/additional";
    }

    @PostMapping("/additional")
    @ResponseBody
    public boolean additional(@RequestBody UserDto userDto, HttpSession httpSessionsession) {
        userDto.setUser_id(httpSessionsession.getAttribute("user_id").toString());
        if(myPageService.additional(userDto)) {
            return true;
        } else {
            return false;
        }
    }

    @GetMapping("/write")
    public String write(HttpSession httpSession, Model model) {
        UserDto user = new UserDto();
        user.setUser_id(httpSession.getAttribute("user_id").toString());
        user.setUser_type(httpSession.getAttribute("user_type").toString());
<<<<<<< HEAD
=======
        log.info(user.getUser_type());
        log.info("유저{}", user.getUser_id());
>>>>>>> origin/copyminyoung
        model.addAttribute("userdto", myPageService.getInfo(user));
        return "mypage/write";
    }
    @PostMapping("/write")
<<<<<<< HEAD
    @ResponseBody
    public boolean write(@RequestBody UserDto userDto){
        if(userDto.getEngineer_regdate() != null) {
            if(myPageService.update_info(userDto)&&myPageService.update_enginner_info(userDto)){
                return true;
            }else {
                return false;
            }
        }else {
            if(myPageService.update_info(userDto){
                return true;
            }
        }
        return false;
=======
//    @ResponseBody
    public String write(UserDto userDto,HttpSession session){
        log.info("테스트해보자");

        userDto.setUser_id((String) session.getAttribute("user_id"));
        if(userDto.getEngineer_regdate()!=null){
            log.info("테스트해보자2");
            if(myPageService.updateInfo(userDto)&&myPageService.updateEngineerInfo(userDto)){
                return "/mypage/write";
            }else{
                return "/mypage/additional";
            }
        }else{
            if(myPageService.updateInfo(userDto)){
                log.info("true");
                return "/mypage/write";
            }else{
                log.info("false");
                return "/mypage/additional";
            }
        }


>>>>>>> origin/copyminyoung
    }

}
