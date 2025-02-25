package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.TakeoffDao;
import com._401unauthorized.sheep.dto.ApplyDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class TakeoffService {
    private final TakeoffDao takeoffDao;

    public void call(ApplyDto applyDto) {
      takeoffDao.call(applyDto);
/*
        if (applydto.getApply_status().equals("0")) {
            applydto.setApply_status("진행");
        }*/
    }
}
