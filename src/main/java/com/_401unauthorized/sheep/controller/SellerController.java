package com._401unauthorized.sheep.controller;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/seller")
@Controller
public class SellerController {
    @GetMapping("/main")
    public String main(HttpSession httpSession, Model model) {
        return "/seller/main";
    }

    @PostMapping("/write")
    public String write(HttpSession httpSession, Model model) {
        return "/seller/write";
    }

    @GetMapping("/review/list")
    public String reviewList(HttpSession httpSession, Model model) {
        return "/seller/review/list";
    }
}
