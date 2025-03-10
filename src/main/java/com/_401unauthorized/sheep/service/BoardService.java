package com._401unauthorized.sheep.service;


import com._401unauthorized.sheep.dao.BoardDao;
import com._401unauthorized.sheep.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardService {
   private final BoardDao boardDao;

    public boolean write(BoardDto employmentDto) {
        boolean result = BoardDao.write(employmentDto);
        if (result) {
        return true;
        }
        return false;
    }
}
