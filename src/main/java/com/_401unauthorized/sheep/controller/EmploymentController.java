package com._401unauthorized.sheep.controller;

import com._401unauthorized.sheep.service.EmploymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/employment")
@Controller
public class EmploymentController {
    private final EmploymentService employmentService;

}
