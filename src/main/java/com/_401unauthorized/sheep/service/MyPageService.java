package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.MyPageDao;
import com._401unauthorized.sheep.dto.UserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Slf4j
@RequiredArgsConstructor
@Service
public class MyPageService {
    private final MyPageDao myPageDao;

    public boolean additional(UserDto userDto, Model model) {

    }
}
