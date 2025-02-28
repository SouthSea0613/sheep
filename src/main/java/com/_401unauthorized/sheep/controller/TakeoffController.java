package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.ApplyDto;
import com._401unauthorized.sheep.dto.CategoryListDto;
import com._401unauthorized.sheep.dto.TakeoffSellerDto;
import com._401unauthorized.sheep.dto.WishDto;
import com._401unauthorized.sheep.service.TakeoffService;
import com._401unauthorized.sheep.service.WishService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/takeoff")
@Controller
public class TakeoffController {
    private final TakeoffService takeoffService;
    private final WishService wishService;

    @PostMapping("/call")
    @ResponseBody
    public boolean call(@RequestBody ApplyDto applydto, HttpSession httpSession, RedirectAttributes rttr, Model model) {
        log.info("살려줘");
        log.info(String.valueOf(applydto));
        boolean result = takeoffService.call(applydto);
        if (result) {
            log.info("컨트롤러 true");
            return true;
        } else {
            log.info("컨트롤러 false");
            return false;
        }
    }

    @GetMapping("seller/write")
    public String write(@RequestParam("wish_number") Integer wish_number, Model model) {
        WishDto wishDto = wishService.essential(wish_number);
        List<CategoryListDto> categoryListDto = wishService.category(wish_number);
        if (wishDto != null) {
            model.addAttribute("wish_dto", wishDto);
            model.addAttribute("category_list_dto", categoryListDto);
        }
        return "takeoff/seller/write";
    }

    @PostMapping("seller/write")
    public String write(@RequestParam("wish_number") String wish_number,
                        @RequestParam("category_number") List<String> category_number,
                        @RequestParam("takeoff_content") List<String> takeoff_content,
                        @RequestParam("takeoff_money") List<String> takeoff_money,
                        HttpSession httpSession) {
        String user_id = (httpSession.getAttribute("user_id").toString());
        TakeoffSellerDto takeoffsellerDto = new TakeoffSellerDto();
        takeoffsellerDto.setUser_id(user_id);
        takeoffsellerDto.setWish_number(wish_number);
        List<TakeoffSellerDto> takeoffsellerdto = new ArrayList<>();

        for (int i = 0; i < category_number.size(); i++) {
            TakeoffSellerDto takeoff_seller_dto = new TakeoffSellerDto();
            takeoff_seller_dto.setUser_id(user_id);
            switch (category_number.get(i)) {
                case "철거":
                    takeoff_seller_dto.setCategory_number("1");
                    break;
                case "목공":
                    takeoff_seller_dto.setCategory_number("2");
                    break;
                case "도배":
                    takeoff_seller_dto.setCategory_number("3");
                    break;
                case "전기":
                    takeoff_seller_dto.setCategory_number("4");
                    break;
                case "조명":
                    takeoff_seller_dto.setCategory_number("5");
                    break;
                case "타일":
                    takeoff_seller_dto.setCategory_number("6");
                    break;
                case "필름":
                    takeoff_seller_dto.setCategory_number("7");
                    break;
                case "도장":
                    takeoff_seller_dto.setCategory_number("8");
                    break;
                case "부엌":
                    takeoff_seller_dto.setCategory_number("9");
                    break;
                case "욕실":
                    takeoff_seller_dto.setCategory_number("10");
                    break;
                case "수납":
                    takeoff_seller_dto.setCategory_number("11");
                    break;
                case "바닥":
                    takeoff_seller_dto.setCategory_number("12");
                    break;
                case "창호":
                    takeoff_seller_dto.setCategory_number("13");
                    break;
                case "도어":
                    takeoff_seller_dto.setCategory_number("14");
                    break;
                case "중문":
                    takeoff_seller_dto.setCategory_number("15");
                    break;
                case "공사준비/마감":
                    takeoff_seller_dto.setCategory_number("16");
                    break;
            }
            takeoff_seller_dto.setWish_number(wish_number);
            takeoff_seller_dto.setWish_category_seller_answer(takeoff_content.get(i));
            takeoff_seller_dto.setWish_category_seller_price(takeoff_money.get(i));

            takeoffsellerdto.add(takeoff_seller_dto);
        }
        if (takeoffService.write(takeoffsellerdto, user_id, wish_number)) {
            return "redirect:/seller/my_list";
        }
        return "redirect:/seller/write>wish_number=" + wish_number;
    }

    @GetMapping("/seller/list")
    public String seller() {
        return "/takeoff/seller/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam("wish_number") Integer wish_number, Model model) {
        if (wish_number == null || wish_number < 1) {
            return "redirect:/list";
        }
        WishDto wishDto = wishService.essential(wish_number);
        List<CategoryListDto> categoryListDtoList = takeoffService.category(wish_number);
        if (wishDto != null) {
            model.addAttribute("wishDto", wishDto);
            model.addAttribute("categoryListDtoList", categoryListDtoList);
            return "/detail";
        } else {
            return "redirect:/list";
        }
    }
}

