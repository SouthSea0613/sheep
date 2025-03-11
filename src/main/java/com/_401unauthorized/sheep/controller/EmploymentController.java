package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.BoardDto;
import com._401unauthorized.sheep.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employment")
@Controller
public class EmploymentController {
    private final EmploymentService employmentService;

    @GetMapping("/write")
    public String write() {
        return "/employment/write";
    }

    @PostMapping("/write")
    public String write(BoardDto employmentDto, Model model) {
        boolean employment_list = employmentService.write(employmentDto);
        model.addAttribute("employment_list", employment_list);
        return "redirect:/employment/list";
    }

    @GetMapping("/list")
    public String list(BoardDto boardDto, Model model) {
        if (boardDto.getPage_number() == null || boardDto.getPage_number() < 1) {
            boardDto.setPage_number(1);
        }

        if (boardDto.getList_count() == null) {
            boardDto.setList_count(employmentService.list_count);
        }

        if (boardDto.getStart_index() == null) {
            boardDto.setStart_index(0);
        }

        return "/employment/list";
    }
}
