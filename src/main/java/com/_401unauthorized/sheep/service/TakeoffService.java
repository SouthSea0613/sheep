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

<<<<<<< HEAD
    public boolean call(ApplyDto applyDto) {
      boolean result = takeoffDao.call(applyDto);
        if(result){
            log.info("서비스 true");
            return true;
        }else{
            log.info("서비스 false");
            return false;
        }

=======
    public void call(ApplyDto applyDto) {
        takeoffDao.call(applyDto);
>>>>>>> jieun
    }
}
