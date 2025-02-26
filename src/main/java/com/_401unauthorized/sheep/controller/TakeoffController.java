package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.service.TakeoffService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/takeoff")
@Controller
public class TakeoffController {
    private final TakeoffService takeoffService;

    @PostMapping("/call")
    @ResponseBody
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
    @GetMapping("/seller/list")
    public String seller(){
        return "/takeoff/seller/list";
    }
}
