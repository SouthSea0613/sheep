package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.EmploymentDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmploymentService {
    private final EmploymentDao employmentDao;
}
