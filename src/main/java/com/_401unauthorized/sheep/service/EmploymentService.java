package com._401unauthorized.sheep.service;


import com._401unauthorized.sheep.dao.EmploymentDao;
import com._401unauthorized.sheep.dto.BoardDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<BoardDto> get_board_list(Integer page_number) {

        // 0번째: 0~9 / 1번째: 10~19 .... 1페이지가 0번째 여야하니까
        int start_index = (page_number - 1) * 10;
        return employmentDao.get_board_list(start_index);
    }

    @Transactional
    public boolean resume_write(BoardDto boarddto, Integer parent_board_number) {
        if(!employmentDao.resume_write(boarddto)){
            return false;
        }
        if(!employmentDao.resume_write2(boarddto.getBoard_number(), boarddto.getUser_id(), parent_board_number)){
            return false;
        }
        return true;
    }
}
