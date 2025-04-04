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
        if (myPageService.additional(userDto)) {
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
        model.addAttribute("userdto", myPageService.get_info(user));
        return "mypage/write";
    }

    @PostMapping("/write")
    public String write(UserDto userDto, HttpSession httpSession) {
        userDto.setUser_id((String) httpSession.getAttribute("user_id"));
        userDto.setUser_type((String) httpSession.getAttribute("user_type"));
        if (myPageService.update_info(userDto)) {
            return "redirect:/mypage/write";
        } else {
            return "/mypage/additional";
        }
    }
}
