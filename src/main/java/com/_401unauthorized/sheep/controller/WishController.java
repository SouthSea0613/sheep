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
    public String write(WishDto wishDto,
                        @RequestParam("category_number") List<Integer> category_number,
                        @RequestParam("major_text") List<String> major_text,
                        HttpSession httpSession) {
        // 세션에 들어있는 유저아이디 를 꺼내! -> 세션이 들어간 이유, 글을 쓰려면 누가 쓰는지 알아야겠지
        String user_id = (httpSession.getAttribute("user_id").toString());
        // 꺼내서 위시디티오에 유저아이디를 넣어놓고
        wishDto.setUser_id(user_id);
        //리스트 두개를 만들어놓자, 우선 대분류 와 중분류!
        //위에 보면 리퀘스트파람 으로 write.html 에서 name 속성들로 묶어서 파라미터로 델꼬옴
        List<MajorDto> major = new ArrayList<>();
        List<SubDto> sub = new ArrayList<>();

        //카테고리 넘버 라는 리스트 가져왔었지? 그녀석 사이즈(길이) 만크 반복문 돌리자! (대분류15개니까~)
        for (int i = 0; i < category_number.size(); i++) {
            if (category_number.get(i)<16) {
                // i가 0번 부터 15번 까지 총 16번 돌거야~
                //메이져디티오 를 새로선언(초기화)
                MajorDto majorDto = new MajorDto();
                // 새로 선언한 디티오에 카테고리넘버 를 순차적으로 넣을거야! (스트링.벨류오브 는 스트링으로 변환시켜줘)
                majorDto.setCategory_number(String.valueOf(category_number.get(i)));
                // 같은 방법으로 메이져텍스트 도 넣어줄거야, 근데 우리 i는 0번부터/ 실제 카테고리넘버들은 1번부터 이기에
                // - 1 넣어야 함!
                majorDto.setMajor_text(major_text.get(category_number.get(i) - 1));
                // 그럼 디티오에 대분류 카테고리넘버 와 메이져텍스트 를 넣었어 ! 그리곤 major 리스트! 에 넣자!
                major.add(majorDto);
            } else {
                //서브카테고리도 같은 맥락으로 한번 독해 해 보자!
                SubDto subDto = new SubDto();
                subDto.setCategory_number(String.valueOf(category_number.get(i)));
                sub.add(subDto);
            }
        }
        // 이제 찐 위시디티오에 아까 만들었던 리스트 2놈 major / sub 를 넣어!
        wishDto.setMajor_category(major);
        wishDto.setSub_category(sub);
        if (wishService.write(wishDto)) {
            return "redirect:/wish/list";
        }
        return "redirect:/wish/write";
    }
}
