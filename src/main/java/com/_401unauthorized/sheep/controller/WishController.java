package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.CategoryListDto;
import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.SubDto;
import com._401unauthorized.sheep.dto.WishDto;
import com._401unauthorized.sheep.service.TakeoffService;
import com._401unauthorized.sheep.service.WishService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/wish")
@Controller
public class WishController {
    private final WishService wishService;
    private final TakeoffService takeoffService;

    @GetMapping("/list")
    public String get_wish_list(HttpSession httpSession, Model model) {
        String user_id = httpSession.getAttribute("user_id").toString();
        model.addAttribute("wish_list", wishService.get_wish_list(user_id));
        model.addAttribute("wish_req_list",wishService.get_wish_req_list(user_id));
        Integer wish_number = wishService.get_wish_req_list(user_id).getWish_number();
        model.addAttribute("takeoff",takeoffService.get_takeoff(user_id,wish_number));
        log.info(wishService.get_wish_list(user_id).toString());
        return "wish/list";
    }

    @GetMapping("/write")
    public String write() {
        return "wish/write";
    }

    @GetMapping("/get_wish_detail")
    public String get_wish_detail(@RequestParam("wish_number") Integer wish_number, Model model) {
        if (wish_number == null || wish_number < 1) {
            return "redirect:/wish/list";
        }
        WishDto wishDto = wishService.essential(wish_number);
        List<CategoryListDto> categoryListDto = wishService.category(wish_number);
        if (wishDto != null) {
            model.addAttribute("wish", wishDto);
            model.addAttribute("categoryListDto", categoryListDto);
            return "wish/detail";
        } else {
            return "redirect:/wish/list";
        }
    }

    @PostMapping("/write")
    public String write(WishDto wishDto, @RequestParam("category_number") List<Integer> category_number, @RequestParam("major_text") List<String> major_text, HttpSession httpSession) {
        String user_id = (httpSession.getAttribute("user_id").toString());
        wishDto.setUser_id(user_id);
        List<MajorDto> major = new ArrayList<>();
        List<SubDto> sub = new ArrayList<>();

        for (int i = 0; i < category_number.size(); i++) {
            if (category_number.get(i) < 16) {
                MajorDto majorDto = new MajorDto();
                majorDto.setCategory_number(String.valueOf(category_number.get(i)));
                majorDto.setMajor_text(major_text.get(category_number.get(i) - 1));
                major.add(majorDto);
            } else {
                SubDto subDto = new SubDto();
                subDto.setCategory_number(String.valueOf(category_number.get(i)));
                sub.add(subDto);
            }
        }
        wishDto.setMajor_category(major);
        wishDto.setSub_category(sub);
        if (wishService.write(wishDto)) {
            return "redirect:/wish/list";
        }
        return "redirect:/wish/write";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("wish_number") Integer wish_number, Model model) {
        if (wish_number == null || wish_number < 1) {
            return "redirect:/wish/list";
        }

        WishDto wishDto = wishService.essential(wish_number);
        List<CategoryListDto> categoryListDto = wishService.category(wish_number);

        if (wishDto != null) {
            model.addAttribute("wish_dto", wishDto);
            model.addAttribute("category_list_dto", categoryListDto);
            return "wish/detail";
        } else {
            return "redirect:/wish/list";
        }
    }

    @GetMapping("/update")
    public String update(@RequestParam("wish_number") Integer wish_number, Model model) {
        model.addAttribute("wish_dto", wishService.essential(wish_number));
        model.addAttribute("category_list_dto", wishService.category(wish_number));
        return "wish/write";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("wish_number") Integer wish_number) {
        if (wishService.delete_wish(wish_number)) {
            return "redirect:/wish/list";
        } else {
            return "redirect:/wish/detail?wish_number=" + wish_number;
        }
    }

    @PostMapping("/update")
    public String update(@RequestParam("wish_number") Integer wish_number, HttpSession httpSession){
        return "redirect:/wish/detail";
    }
}
