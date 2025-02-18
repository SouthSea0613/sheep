package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.WishDao;
import com._401unauthorized.sheep.dto.WishDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class WishService {
    private final WishDao wishDao;
    public String write(HttpSession httpSession, Model model) {

    }
}
