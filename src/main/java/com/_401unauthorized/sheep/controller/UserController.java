package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.UserDto;
import com._401unauthorized.sheep.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user")
@Controller
public class UserController {
    private final UserService userService;

    @GetMapping("/join")
    public String join() {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(UserDto userDto) {
        if (userService.service_join(userDto)) {
            return "redirect:/";
        }
        return "index";
    }

    @PostMapping("/id_check")
    @ResponseBody
    public boolean id_check(@RequestBody UserDto userDto) {
        if (userService.id_check(userDto.getUser_id())) {
            return false;
        }
        return true;
    }

    @PostMapping("/email_check")
    @ResponseBody
    public boolean email_check(@RequestBody UserDto userDto) {
        if (userService.email_check(userDto.getUser_email())) {
            return false;
        }
        return true;
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@RequestBody UserDto userDto, HttpSession session) {
        if (userService.login(userDto)) {
            session.setAttribute("user_id", userDto.getUser_id());
            session.setAttribute("user_type", userDto.getUser_type());
            return true;
        }
        return false;
    }
}