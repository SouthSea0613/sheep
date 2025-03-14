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

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employment")
@Controller
public class EmploymentController {
    private final EmploymentService employmentService;

    @PostMapping("/write")
    public String write() {
        return "employment/write";
    }

    @PostMapping("/insert_write")
    public String write(BoardDto employmentDto, Model model) {
        boolean employment_list = employmentService.write(employmentDto);
        model.addAttribute("employment_list", employment_list);
        return "redirect:/employment/list";
    }

    @GetMapping("/select_area")
    public String select_area(Model model) {
        model.addAttribute("action", "/employment/write");
        log.info("action: {}", model.getAttribute("action"));
        return "takeoff/seller/select_area";
    }

//    @PostMapping("/select_area")
//    public String select_area(BoardDto boardDto, @RequestParam("job_area") String job_area) {
//        Integer board_number = boardDto.getBoard_number();
//        if (employmentService.select_area(board_number, job_area)) {
//            return "redirect:/employment/write";
//        }
//        return "redirect:/takeoff/seller/select_area";
//    }

    @GetMapping("/detail")
    public String detail(@RequestParam("board_number") Integer board_number, Model model) {
        if (board_number == null || board_number < 1) {
            return "redirect:/employment/list";
        }
        BoardDto employmentDto = employmentService.detail(board_number);
        if (employmentDto != null) {
            model.addAttribute("employmentDto", employmentDto);
            return "employment/detail";
        } else {
            return "redirect:/employment/list";
        }
    }

    @GetMapping("/list")
    public String list(@RequestParam("page_number") Integer page_number, Model model) {
        List<BoardDto> boarddto = employmentService.get_board_list(page_number);
        model.addAttribute("boarddto", boarddto);
        return "employment/list";
    }
}

