package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.WishDto;
import com._401unauthorized.sheep.service.WishService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wish")
@Controller
public class WishController {
    private final WishService wishService;

    @GetMapping("/list")
    public String get_wish_list(HttpSession httpSession, Model model) {
        String user_id = httpSession.getAttribute("user_id").toString();
        model.addAttribute("wish_list", wishService.get_wish_list(user_id));
        return "wish/list";
    }
    @GetMapping("/write")
    public String write(){
        return "wish/write";
    }
    @PostMapping("/write")
    public String wish(HttpSession session, WishDto wishDto){

        return "";
    }




}
