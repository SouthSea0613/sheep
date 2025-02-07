package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.service.TakeoffService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/takeoff")
@Controller
public class TakeoffController {
    private final TakeoffService takeoffService;
}
