package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.ApplyDto;
<<<<<<< HEAD
import com._401unauthorized.sheep.dto.WishDto;
=======
>>>>>>> jieun
import com._401unauthorized.sheep.service.TakeoffService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
=======
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
>>>>>>> jieun

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/takeoff")
@Controller
public class TakeoffController {
    private final TakeoffService takeoffService;

<<<<<<< HEAD
    //    @GetMapping("/call")
//    public String call(){
//        return "takeoff/call";
//    }
    @PostMapping("/call")
    public boolean call(@RequestBody ApplyDto applydto, HttpSession httpSession, RedirectAttributes rttr, Model model) {
        log.info("살려줘");
        log.info(String.valueOf(applydto));
        boolean result = takeoffService.call(applydto);
        if(result){
            log.info("컨트롤러 true");
            return true;
        }else{
            log.info("컨트롤러 false");
            return false;
        }
    }

=======
    @GetMapping("/call")
    public String call(@RequestParam("wish_number") Integer wish_number, HttpSession httpSession, Model model) {
        log.info("살려줘");
        ApplyDto applydto = new ApplyDto();
        applydto.setUser_id((String) httpSession.getAttribute("user_id"));
        applydto.setApply_status("0");
        applydto.setWish_number(String.valueOf(wish_number));
        takeoffService.call(applydto);
        model.addAttribute("apply_status", applydto.getApply_status());
        return "redirect:/wish/list";
    }
>>>>>>> jieun
}

//    @GetMapping("/seller/list")
//    public String seller_list() {
//        takeoffService.seller_list();
//        return "/seller/list";
//    }
