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

import java.util.HashMap;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employment")
@Controller
public class EmploymentController {
    private final EmploymentService employmentService;

    @GetMapping("/write")
    public String write() {
        return "employment/write";
    }

    @PostMapping("/write")
    public String write(BoardDto employmentDto, Model model) {
        boolean employment_list = employmentService.write(employmentDto);
        model.addAttribute("employment_list", employment_list);
        return "redirect:/employment/list";
    }

//    @GetMapping("/list")
//    public String list(BoardDto boardDto, Model model, HttpSession httpSession) {
//        if (boardDto.getPage_number() == null || boardDto.getPage_number() < 1) {
//            boardDto.setPage_number(1);
//        }
//
//        if (boardDto.getList_count() == null) {
//            boardDto.setList_count(employmentService.list_count);
//        }
//
//        if (boardDto.getStart_index() == null) {
//            boardDto.setStart_index(0);
//        }
//
//        List<BoardDto> employment_list = null;
//        employment_list = employmentService.list(boardDto);
//        if (boardDto != null) {
//            String pageHtml = employmentService.paging(boardDto);
//            if (boardDto.getColname() != null) {
//                httpSession.setAttribute("boardDto", boardDto);
//            } else {
//                httpSession.removeAttribute("boardDto");
//                httpSession.setAttribute("boardDto", boardDto.getPage_number());
//            }
//            model.addAttribute("paging", pageHtml);
//            model.addAttribute("employment_list", employment_list);
//            return "employment/list";
//        }
//        return "employment/list";
//    }
}
