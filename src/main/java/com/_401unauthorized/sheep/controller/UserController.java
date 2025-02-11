package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.SellerDto;
import com._401unauthorized.sheep.dto.UserDto;
import com._401unauthorized.sheep.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
<<<<<<< HEAD
    public String join(UserDto userDto, Model model) {
        if(userService.service_join(userDto)){
            model.addAttribute("user_id", userDto.getUser_id());
            return "user/join_success";
        }
        else {
            return "redirect:/user/join";
=======
    public String join(UserDto userDto) {
        if (userService.service_join(userDto)) {
            return "redirect:/";
>>>>>>> copyuser_jieun
        }
    }

    @GetMapping("/join_additional")
    public String join_additional() {
        return "user/join_additional";
    }

    //비동기 axios -------------------------------------------------
    @PostMapping("/id_check")
    @ResponseBody
    public boolean id_check(@RequestBody UserDto userDto) {
        if (userService.id_check(userDto.getUser_id())) {
            return false;
        }
        return true;
    }
<<<<<<< HEAD
    @PostMapping("/email_check")
    @ResponseBody
    public boolean email_check(@RequestBody UserDto userDto){
        if(userService.email_check(userDto.getUser_email())){
=======

    @PostMapping("/email_check")
    @ResponseBody
    public boolean email_check(@RequestBody UserDto userDto) {
        if (userService.email_check(userDto.getUser_email())) {
>>>>>>> copyuser_jieun
            return false;
        }
        return true;
    }
<<<<<<< HEAD
    @PostMapping("/seller_regnum_check")
    @ResponseBody
    public boolean seller_regnum_check(@RequestBody SellerDto sellerDto){
        if(userService.seller_regnum_check(sellerDto.getSeller_regnum())){
            return false;
        }
        return true;
    }
    //-----------------------------------------------------------
=======
>>>>>>> copyuser_jieun

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

<<<<<<< HEAD
    @PostMapping("/join_additional_seller")
    public String join_additional_seller(SellerDto sellerDto){
        if(userService.join_additional_seller(sellerDto)){
            return "redirect:/user/login";
        }
        return "redirect:/user/join_additional";
    }

}
=======
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
>>>>>>> copyuser_jieun
