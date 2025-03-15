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
            log.info("boardDto = {}", boardDto);
            BoardDto jobDto = new BoardDto();
            jobDto.setBoard_number(boardDto.getBoard_number());
            jobDto.setJob_count(boardDto.getJob_count());
            jobDto.setJob_area(boardDto.getJob_area());
            employmentDao.insert_job(jobDto);
            return true;
        }
        return false;
    }

    public BoardDto detail(Integer board_number) {
        BoardDto employmentDto = employmentDao.detail(board_number);
        log.info("employmentDto.user_id = {}", employmentDto.getUser_id());
        return employmentDto;
    }

    public List<BoardDto> get_board_list(Integer page_number) {

        // 0번째: 0~9 / 1번째: 10~19 .... 1페이지가 0번째 여야하니까
        int start_index = (page_number - 1) * 10;
        List<BoardDto> boarddto = employmentDao.get_board_list(start_index);
        log.info("boarddto = {}", boarddto);
        for (BoardDto employment : boarddto) {
            if (employment.getJob_status() != null) {
                switch (employment.getJob_status()) {
                    case "0":
                        employment.setJob_status("모집중");
                        break;

                    case "1":
                        employment.setJob_status("마감");
                        break;
                }
            }
        }
        return boarddto;
    }

    @Transactional
    public boolean resume_write(BoardDto boarddto) {
        if (!employmentDao.resume_write(boarddto)) {
            return false;
        }
        if (!employmentDao.resume_write2(boarddto)) {
            return false;
        }
        return true;
    }

    public boolean complete(Integer board_number) {
        return employmentDao.complete(board_number);
    }

    public List<BoardDto> resume_list(Integer board_number) {
        log.info("board_number = {}", board_number);
        List<BoardDto> profileDto = employmentDao.resume_list(board_number);
        log.info("profileDto = {}", profileDto);
        return profileDto;
    }

    public BoardDto profile_detail(Integer board_number) {
        return employmentDao.profile_detail(board_number);
    }

    public boolean lets_do_it(Integer board_number) {
        return employmentDao.lets_do_it(board_number);
    }
}
