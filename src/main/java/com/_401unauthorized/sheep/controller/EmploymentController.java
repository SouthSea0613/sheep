package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.BoardDto;
import com._401unauthorized.sheep.service.EmploymentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employment")
@Controller
public class EmploymentController {
    private final EmploymentService employmentService;

    @GetMapping("/write")
    public String job_write() {
        return "employment/write";
    }

    @PostMapping("/write")
    public String write(@RequestParam("seller_area") String seller_area, Model model) {
        model.addAttribute("seller_area", seller_area);
        return "employment/write";
    }

    @PostMapping("/insert_write")
    public String write(BoardDto employmentDto, Model model) {
        boolean employment_list = employmentService.write(employmentDto);
        model.addAttribute("employment_list", employment_list);
        return "redirect:/employment/list?page_number=1";
    }

    @GetMapping("/select_area")
    public String select_area(Model model) {
        model.addAttribute("action", "/employment/write");
        model.addAttribute("seller_area", "/employment/write");
        log.info("action: {}", model.getAttribute("action"));
        return "takeoff/seller/select_area";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("board_number") Integer board_number, Model model) {
        if (board_number == null || board_number < 1) {
            return "redirect:/employment/list?page_number=1";
        }
        BoardDto employmentDto = employmentService.detail(board_number);
        if (employmentDto != null) {
            List<BoardDto> profileDto = employmentService.resume_list(board_number);
            model.addAttribute("employmentDto", employmentDto);
            model.addAttribute("profileDto", profileDto);
            return "employment/detail";
        } else {
            return "redirect:/employment/list?page_number=1";
        }
    }

    @GetMapping("/list")
    public String list(@RequestParam("page_number") Integer page_number, Model model) {
        List<BoardDto> boarddto = employmentService.get_board_list(page_number);
        model.addAttribute("boarddto", boarddto);
        return "employment/list";
    }

    @PostMapping("/resume_write")
    public String resume_write(BoardDto boarddto, HttpSession session) {
        boarddto.setUser_id(session.getAttribute("user_id").toString());
        if (employmentService.resume_write(boarddto)) {
            return "redirect:/employment/detail?board_number=" + boarddto.getParent_board_number();
        }
        return "redirect:/employment/resume/write";
    }

    @GetMapping("/resume/complete")
    public String complete(@RequestParam("board_number") Integer board_number) {
        if (employmentService.resume_complete(board_number)) {
            return "redirect:/employment/list?page_number=1";
        }
        return "redirect:/employment/detail";
    }

    @GetMapping("/resume/write")
    public String resume_write() {
        return "employment/resume/write";
    }

    @GetMapping("/resume/detail")
    public String resume_detail(@RequestParam("board_number") Integer board_number, Model model) {
        log.info("board_number: {}", board_number);
        if (board_number == null || board_number < 1) {
            return "redirect:/employment/detail";
        }
        BoardDto profileDto = employmentService.profile_detail(board_number);
        if (profileDto != null) {
            model.addAttribute("profileDto", profileDto);
            return "employment/resume/detail";
        }
        return "redirect:/employment/detail?board_number=" + board_number;
    }

    @PostMapping("/lets_do_it")
    public String lets_do_it(BoardDto doDto) {
        log.info("doDto: {}", doDto);
        boolean result = employmentService.lets_do_it(doDto);
        log.info("result: {}", result);
        if (result) {
            return "redirect:/employment/list?page_number=1";
        }
        return "redirect:/employment/resume/detail?board_number=" + doDto.getParent_board_number();
    }
}

