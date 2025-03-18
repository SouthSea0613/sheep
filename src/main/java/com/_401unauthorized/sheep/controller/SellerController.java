package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.BoardDto;
import com._401unauthorized.sheep.service.SellerService;
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
    private final SellerService sellerService;

    @GetMapping("/main")
    public String main(HttpSession httpSession, Model model) {
        BoardDto boardDto = sellerService.seller_main(httpSession.getAttribute("user_id").toString());
        model.addAttribute("board", boardDto);
        return "/seller/main";
    }

    @GetMapping("/write")
    public String write(HttpSession httpSession, Model model) {
        return "/seller/write";
    }

    @PostMapping("/write")
    public String write(HttpSession httpSession, BoardDto board_dto) {
        board_dto.setUser_id(httpSession.getAttribute("user_id").toString());
        sellerService.seller_write_main(board_dto, httpSession.getServletContext().getRealPath("/") + "uploads/");
        return "redirect:/seller/write";
    }

    @GetMapping("/review/list")
    public String reviewList(HttpSession httpSession, Model model) {
        return "/seller/review/list";
    }

    @GetMapping("/review/write")
    public String reviewWrite(HttpSession httpSession, Model model) {
        return "/seller/review/write";
    }

    @GetMapping("/review/detail")
    public String reviewDetail(HttpSession httpSession, Model model) {
        return "/seller/review/detail";
    }
}
