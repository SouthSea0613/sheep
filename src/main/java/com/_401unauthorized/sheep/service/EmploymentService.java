package com._401unauthorized.sheep.service;


import com._401unauthorized.sheep.dao.EmploymentDao;
import com._401unauthorized.sheep.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class EmploymentService {
    public final Integer list_count = 10;
    public final Integer page_count = 2;
    private final EmploymentDao employmentDao;

    public boolean write(BoardDto boardDto) {
        if (employmentDao.write(boardDto)) {
            log.info("boardDto = {}", boardDto.getBoard_number());
            int board_number = boardDto.getBoard_number();
            int job_count = boardDto.getJob_count();
            String job_area = boardDto.getJob_area();
            log.info("job count: {}", job_count);
            log.info("job area: {}", job_area);
            BoardDto jobDto = new BoardDto();
            jobDto.setBoard_number(board_number);
            jobDto.setJob_count(job_count);
            jobDto.setJob_area(job_area);
            employmentDao.insert_job(jobDto);
            return true;
        }
        return false;
    }

    public List<BoardDto> list(BoardDto boardDto) {
       Integer page_number = boardDto.getPage_number();
       boardDto.setStart_index((page_number - 1) * boardDto.getList_count());
       return employmentDao.list(boardDto);
    }


//    public boolean select_area(Integer board_nubmer, String job_area) {
//        return employmentDao.select_area(board_nubmer, job_area);
//    }

//
//    public String paging(BoardDto boardDto) {
//        int total_number = employmentDao.count(boardDto);
//        String url = null;
//        if(boardDto.getColname() != null) {
//            url = "/employment?colname=" + boardDto.getColname() + "&keyword=" + boardDto.getKeyword() + "&";
//        } else {
//            url = "/employment?";
//        }
//
//        Paging paging = new Paging(total_number, boardDto.getPage_number(), list_count, page_count, url);
//    }
}
