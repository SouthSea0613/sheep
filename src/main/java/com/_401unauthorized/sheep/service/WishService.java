package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.WishDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class WishService {
    private final WishDao wishDao;

    public Object get_wish_list(String userId) {

    }
}
