package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.MajorDto;
import com._401unauthorized.sheep.dto.SubDto;
import com._401unauthorized.sheep.dto.WishDto;
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
public class WishController{
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
    public String wish(@RequestParam("category_number") List<Integer> category_number,@RequestParam("major_text") List<String> major_text, HttpSession session,WishDto wishDto){

    log.info(String.valueOf(category_number));
        log.info(major_text.toString());
//        wishDto.setUser_id(session.getAttribute("user_id").toString());
        List<MajorDto> major = new ArrayList<>();
        List<SubDto> sub = new ArrayList<>();

        for(int i=0; i<category_number.size(); i++){


            if(category_number.get(i) <16){
                MajorDto majorDto = new MajorDto();
                majorDto.setCategory_number(String.valueOf(category_number.get(i)));
                majorDto.setMajor_text(major_text.get(category_number.get(i)-1));
                major.add(majorDto);

            }else{
                SubDto subDto = new SubDto();
                subDto.setCategory_number(String.valueOf(category_number.get(i)));
                sub.add(subDto);

            }
            log.info(wishDto.toString());

        }
        wishDto.setMajor_category(major);
        wishDto.setSub_category(sub);
        log.info(wishDto.toString());

//
        return "wish/list";
    }




}
