package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.dto.WishDto;
import com._401unauthorized.sheep.service.TakeoffService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/takeoff")
@Controller
public class TakeoffController {
    private final TakeoffService takeoffService;

    //    @GetMapping("/call")
//    public String call(){
//        return "takeoff/call";
//    }
    @PostMapping("/call")
    public String call(@RequestParam("wish_number") String wish_number, HttpSession httpSession, RedirectAttributes rttr, Model model) {
        log.info("살려줘");
        ApplyDto applydto = new ApplyDto();
        applydto.setUser_id((String) httpSession.getAttribute("user_id"));
        applydto.setApply_status("0");
        applydto.setWish_number(String.valueOf(wish_number));
        boolean result = takeoffService.call(applydto);
        if (result) {
            rttr.addAttribute("msg", "견적 요청 완료되었습니다.");
            return "redirect:/wish/list";
        } else {
            rttr.addAttribute("msg", "견적 요청 실패했습니다.");
            return "redirect:/wish/list";
        }


    }

}
