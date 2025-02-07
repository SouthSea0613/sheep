package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.service.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/seller")
@Controller
public class SellerController {
    private final SellerService sellerService;
}
