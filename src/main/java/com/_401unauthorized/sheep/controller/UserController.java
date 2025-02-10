package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.UserDto;
import com._401unauthorized.sheep.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public String join(UserDto userDto){
        if(userService.service_join(userDto)){
            return "redirect:/";
        }
        return "index";
    }

    @PostMapping("/id_check")
    @ResponseBody
    public boolean id_check(@RequestBody UserDto userDto){
        if(userService.id_check(userDto.getUser_id())){
            return false;
        }
        return true;
    }
}
