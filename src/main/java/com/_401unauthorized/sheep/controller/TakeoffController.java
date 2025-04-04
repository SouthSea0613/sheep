package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.dto.*;
import com._401unauthorized.sheep.service.SellerService;
import com._401unauthorized.sheep.service.TakeoffService;
import com._401unauthorized.sheep.service.UserService;
import com._401unauthorized.sheep.service.WishService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/takeoff")
@Controller
public class TakeoffController {
    private final TakeoffService takeoffService;
    private final WishService wishService;
    private final SellerService sellerService;
    private final UserService userService;

    // (소비자) 위시리스트 에서 <견적요청>! -----------------------------
    @PostMapping("/call")
    @ResponseBody
    public boolean call(@RequestBody ApplyDto applydto) {
        boolean result = takeoffService.call(applydto);
        if (result) {
            return true;
        } else {
            return false;
        }
    }

    // (업체) 들어온 위시리스트 에서 <견적쓰기>! -------------------------------------------------
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

    // (업체) <견적쓰기> 에서 다 작성하고, <견적작성>! --------------------------------------------------
    @PostMapping("seller/write")
    public String write(@RequestParam("wish_number") Integer wish_number, @RequestParam("category_number") List<String> category_number, @RequestParam("takeoff_content") List<String> takeoff_content, @RequestParam("takeoff_money") List<String> takeoff_money, HttpSession httpSession) {
        String user_id = (httpSession.getAttribute("user_id").toString());
        TakeoffSellerDto takeoffsellerDto = new TakeoffSellerDto();
        takeoffsellerDto.setUser_id(user_id);
        takeoffsellerDto.setWish_number(String.valueOf(wish_number));
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
            takeoff_seller_dto.setWish_number(String.valueOf(wish_number));
            takeoff_seller_dto.setWish_category_seller_answer(takeoff_content.get(i));
            takeoff_seller_dto.setWish_category_seller_price(takeoff_money.get(i));

            takeoffsellerdto.add(takeoff_seller_dto);
        }
        if (takeoffService.write(takeoffsellerdto, user_id, wish_number)) {
            return "redirect:/takeoff/seller/list";
        }
        return "redirect:/seller/write?wish_number=" + wish_number;
    }

    // (판매자 & 소비자) 판: 내가준 견적리스트 / 소: 들어온 견적리스트---------------------------------
    @GetMapping("/seller/detail")
    public String detail(@RequestParam("wish_number") Integer wish_number, @RequestParam("user_id") String user_id, Model model,HttpSession httpSession) {
        if (wish_number == null || wish_number < 1) {
            return "takeoff/seller/list";
        }

        WishDto takeoffDto = takeoffService.essential(wish_number,user_id);
        List<CategoryListDto> takeoffSellerDto = takeoffService.takeoff(wish_number, user_id);
        WishDto wishDto  = wishService.get_status(user_id,wish_number);
        WishDto userStatus = wishService.get_user_status(httpSession.getAttribute("user_id").toString(),wish_number);

        if (takeoffDto != null) {
            model.addAttribute("takeoffDto", takeoffDto);
            model.addAttribute("takeoffSellerDto", takeoffSellerDto);
            model.addAttribute("applystatus", wishDto);
            model.addAttribute("userStatus",userStatus);
            int all = 0;
            for (CategoryListDto price : takeoffSellerDto) {
                all += price.getWish_category_seller_price();
            }
            model.addAttribute("all_money", all);
            return "takeoff/seller/detail";

        } else {
            return "redirect:/takeoff/seller/list";
        }
    }

    // 들어온 위시리스트 & 내가준 견적리스트 (화면)----------------------------------------------------------------------------------------------
    @GetMapping("/seller/list")
    public String seller(HttpSession httpsession, Model model) {
        model.addAttribute("area", userService.getSeller_area(httpsession.getAttribute("user_id").toString()));
        model.addAttribute("seller_takeoff_list",sellerService.seller_list(httpsession.getAttribute("user_id").toString()));
        return "takeoff/seller/list";
    }

    // 들어온 위시리스트 & 내가준 견적리스트 (axios) 리스트 최신화----------------------------------------------------------
    @PostMapping("/list")
    @ResponseBody
    public List<TakeoffDto> list(@RequestBody TakeoffDto takeoffDto){
        List<TakeoffDto> takeoffdtolist = takeoffService.list(takeoffDto.getWish_number());
        return takeoffdtolist;
    }

    @PostMapping("/seller/my_list")
    @ResponseBody
    public List<TakeoffDto> writelist(HttpSession httpsession){
        List<TakeoffDto> takeoffdtolist = takeoffService.my_list(httpsession.getAttribute("user_id").toString());
        return takeoffdtolist;
    }

    // (소비자) 위시리스트 에서 <계약완료>! ---------------------------------------
    @GetMapping("/complete")
    public String complete(@RequestParam("wish_number") Integer wish_number, @RequestParam("user_id") String user_id, HttpSession httpSession) {
        log.info(wish_number.toString());
        String session_user_id = httpSession.getAttribute("user_id").toString();
        if (takeoffService.complete(wish_number, user_id, session_user_id)) {
            log.info("리스트");
            return "redirect:/wish/list";
        } else {
            log.info("디테일");
            return "takeoff/seller/detail";
        }
    }

    @PostMapping("/count")
    @ResponseBody
    public boolean takeoffcount(@RequestParam("wish_number") Integer wish_number,@RequestParam("user_id") String user_id){
        log.info("카운트");
        if(takeoffService.count(wish_number,user_id)){
            return true;
        }
        return false;
    }

    @PostMapping("/checkarea")
    @ResponseBody
    public boolean checkarea(HttpSession httpSession){
        log.info("테스트");
        if(takeoffService.checkarea(httpSession.getAttribute("user_id").toString())){
            return true;
        }
        return false;
    }

    @GetMapping("/seller/select_area")
    public String select_area(Model model) {
        model.addAttribute("action", "/takeoff/seller/select_area");
        return "takeoff/seller/select_area";
    }

    @PostMapping("/seller/select_area")
    public String select_area(HttpSession httpSession,@RequestParam("seller_area") String seller_area) {
        log.info("seller_area: {}", seller_area);
        if(takeoffService.select_area(httpSession.getAttribute("user_id").toString(), seller_area)){
            return "redirect:/takeoff/seller/list";
        }
        return "takeoff/seller/select_area";
    }

//    @PostMapping("/contract")
//    @ResponseBody
//    public boolean contract(@RequestBody WishDto wishdto,HttpSession httpSession){
//        if(takeoffService.contract(wishdto.getWish_number(),wishdto.getUser_id(),httpSession.getAttribute("user_id").toString())){
//            return true;
//        }
//        return false;
//    }

    @GetMapping("/changestatus")
    public String changestatus(@RequestParam Integer wish_number,@RequestParam String user_id){
        if(takeoffService.changestatus(wish_number,user_id)){
            return "redirect:/wish/list";
        }
        return "takeoff/seller/detail";
    }

    @PostMapping("/confirm")
    @ResponseBody
    public boolean confirm(@RequestBody WishDto wishdto,HttpSession httpSession){
        log.info("테스트");
        if(takeoffService.confirm(wishdto.getWish_number(),httpSession.getAttribute("user_id").toString())){
            return true;
        }
        return false;
    }

    @PostMapping("/getstatus")
    @ResponseBody
    public WishDto getstatus(@RequestBody WishDto wishdto){
        return takeoffService.getstatus(wishdto.getUser_id(),wishdto.getWish_number());
    }

    @PostMapping("/endwish")
    @ResponseBody
    public WishDto endwish(HttpSession httpSession){
        return takeoffService.endwish(httpSession.getAttribute("user_id").toString());
    }

    @PostMapping("/endtakeoff")
    @ResponseBody
    public List<TakeoffDto> endtakeoff(@RequestBody TakeoffDto takeoffdto){
        List<TakeoffDto> takeoffdtoList = takeoffService.endtakeoff(takeoffdto.getUser_id(),takeoffdto.getWish_number());
        return takeoffdtoList;
    }
    @PostMapping("/seller/endtakeoff")
    @ResponseBody
    public List<TakeoffDto> endtakeoff(@RequestBody UserDto userdto){
        List<TakeoffDto> takeoffdtoList = takeoffService.endseller_takeoff(userdto.getUser_id());
        return takeoffdtoList;
    }
    @PostMapping("/seller/status")
    @ResponseBody
    public List<TakeoffDto> endtakeoff(@RequestBody TakeoffDto takeoffdto,HttpSession httpSession){
        List<TakeoffDto> statusList = takeoffService.get_seller_status(takeoffdto.getWish_number(),httpSession.getAttribute("user_id").toString());
        return statusList;
    }
}

