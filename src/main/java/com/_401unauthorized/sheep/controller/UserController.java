package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.SellerDto;
import com._401unauthorized.sheep.dto.UserDto;
import com._401unauthorized.sheep.dto.EngineerDto;
import com._401unauthorized.sheep.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String join(UserDto userDto, Model model) {
        if (userService.join(userDto)) {
            model.addAttribute("user_id", userDto.getUser_id());
            return "user/join_success";
        } else {
            return "redirect:/user/join";
        }
    }

    @PostMapping("/id_find_email")
    @ResponseBody
    public boolean id_find_email(@RequestBody UserDto userDto) {
        if(userService.id_find_check(userDto.getUser_email()) != null){
            return false;
        }
        return true;
    }
    @PostMapping("/id_find_email_id")
    @ResponseBody
    public String id_find_email_id(@RequestBody UserDto userDto) {
        return userService.id_find_check(userDto.getUser_email());
    }

    @PostMapping("/id_check")
    @ResponseBody
    public boolean id_check(@RequestBody UserDto userDto) {
        if (userService.id_check(userDto.getUser_id())) {
            return false;
        } else {
            return true;
        }
    }

    @GetMapping("/join_additional")
    public String join_additional() {
        return "user/join_additional";
    }

    @PostMapping("/email_check")
    @ResponseBody
    public boolean email_check(@RequestBody UserDto userDto) {
        if (userService.email_check(userDto.getUser_email())) {
            return false;
        } else {
            return true;
        }
    }

    @PostMapping("/seller_regnum_check")
    @ResponseBody
    public boolean seller_regnum_check(@RequestBody SellerDto sellerDto) {
        if (userService.seller_regnum_check(sellerDto.getSeller_regnum())) {
            return false;
        }
        return true;
    }
    @PostMapping("/engineer_regnum_check")
    @ResponseBody
    public boolean engineer_regnum_check(@RequestBody EngineerDto engineerDto){
        log.info("{}",engineerDto);
        if (userService.engineer_regnum_check(engineerDto.getEngineer_regnum())){
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
    public boolean login(@RequestBody UserDto userDto, HttpSession httpSession) {
        if (userService.login(userDto)) {

            httpSession.setAttribute("user_id", userDto.getUser_id());
            httpSession.setAttribute("user_type", userDto.getUser_type());

            return true;
        } else {
            return false;
        }
    }

    @PostMapping("/join_additional_seller")
    public String join_additional_seller(SellerDto sellerDto) {
        if (userService.join_additional_seller(sellerDto)) {
            return "redirect:/user/login";
        } else {
            return "redirect:/user/join_additional";
        }
    }

    @GetMapping("/id_find")
    public String id_find() {
        return "user/id_find";
    }

    @GetMapping("/pw_reset")
    public String pw_reset() {
        return "user/pw_reset";
    }

    @PostMapping("/pw_reset")
    public String pw_reset(UserDto userDto) {
        if (userService.pw_reset(userDto)) {
            return "redirect:/user/login";
    } else {
        return "redirect:/user/pw_reset";
        }
    }

    @PostMapping("/join_additional_engineer")
    public String join_additional_engineer(EngineerDto engineerDto){
        if(userService.join_additional_engineer(engineerDto)) {
            return "redirect:/user/login";
        }
        return "redirect:/user/join_additional";
    }
}
