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
    //html상에서 폼으로 넘겨준 같은 name속성의 category_number들을 category_number의 리스트들안에 차례로 RequestParam으로 받아온다.
    //html상에서 폼으로 넘겨준 같은 name속성의 major_text들을 major_text의 리스트들안에 차례로 RequestParam으로 받아온다.
    // @ResponseBody - 비동기의 리턴을 위해 필요해!
    public String write(WishDto wishDto,
                        @RequestParam("category_number") List<Integer> category_number,
                        @RequestParam("major_text") List<String> major_text,
                        HttpSession httpSession) {
        // 세션에 들어있는 유저아이디 를 꺼내! -> 세션이 들어간 이유, 글을 쓰려면 누가 쓰는지 알아야겠지
        String user_id = (httpSession.getAttribute("user_id").toString());
        // 꺼내서 위시디티오에 유저아이디를 넣어놓고
        wishDto.setUser_id(user_id);
        //wishDto에 major_category와 sub_category에 넣어줄 임시 리스트 생성
        List<MajorDto> major = new ArrayList<>();
        List<SubDto> sub = new ArrayList<>();

        log.info(category_number.toString());
        log.info(major_text.toString());

        for (int i = 0; i < category_number.size(); i++) {

            if (category_number.get(i)<16) {
                //MajorDto 객체 생성
                // i가 0번 부터 15번 까지 총 16번 돌거야~
                //메이져디티오 를 새로선언(초기화)
                MajorDto majorDto = new MajorDto();
                //MajorDto의 멤버변수 category_number에 선택된 (메이저)category_number들을 for문을 통해서
                //차례대로 넣어준다
                // 새로 선언한 디티오에 카테고리넘버 를 순차적으로 넣을거야! (스트링.벨류오브 는 스트링으로 변환시켜줘)
                majorDto.setCategory_number(String.valueOf(category_number.get(i)));
                //MajorDto의 major_text에 작성이 된 major_text for문을 통해서
                //차례대로 넣어준다
                //대신 category_number는 1부터 시작하지만 인덱스는 0부터 시작하므로 
                //category_number.get(i) - 1을 해줘야한다
                // 같은 방법으로 메이져텍스트 도 넣어줄거야, 근데 우리 i는 0번부터/ 실제 카테고리넘버들은 1번부터 이기에
                // - 1 넣어야 함!
                majorDto.setMajor_text(major_text.get(category_number.get(i) - 1));
                // 그럼 디티오에 대분류 카테고리넘버 와 메이져텍스트 를 넣었어 ! 그리곤 major 리스트! 에 넣자!
                major.add(majorDto);
            } else {
                //SubDto 객체 생성
                //서브카테고리도 같은 맥락으로 한번 독해 해 보자!
                SubDto subDto = new SubDto();
                //SubDto의 멤버 변수 category_number에 선택된 (서브)category_number들을 for문을 통해서
                //차례대로 넣어준다
                subDto.setCategory_number(String.valueOf(category_number.get(i)));
                //데이터를 subDto에 넣어준후 임시 sub리스트에 넣어준다
                sub.add(subDto);

            }
        }
        //데이터를 넣어준 임시 리스트들을 wishDto에 major_category와 sub_category에 넣어준다
        // 이제 찐 위시디티오에 아까 만들었던 리스트 2놈 major / sub 를 넣어!
        wishDto.setMajor_category(major);
        wishDto.setSub_category(sub);
        log.info(wishDto.toString());
        if (wishService.write(wishDto)) {
            return "redirect:/wish/list";
        }
        return "redirect:/wish/write";
    }
}
