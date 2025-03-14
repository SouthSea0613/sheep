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

    public List<BoardDto> list() {
        List<BoardDto> employment_board_list = employmentDao.list();
        log.info("employment_board_list = {}", employment_board_list);
        for (BoardDto employment : employment_board_list) {
            if (employment.getBoard_status() != null){
                switch (employment.getBoard_status()) {
                    case "0":
                        employment.setBoard_status("모집중");
                        break;

                    case "1":
                        employment.setBoard_status("마감");
                        break;
                }
            }
        }
        return employment_board_list;
    }

    public BoardDto detail(Integer board_number) {
        BoardDto employmentDto = employmentDao.detail(board_number);
        log.info("employmentDto = {}", employmentDto);
        return employmentDto;
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
