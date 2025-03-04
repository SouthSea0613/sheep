package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.SellerDao;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerDao sellerDao;

    public List<String> seller_list(String userId) {
        List<String> sellerlist = sellerDao.seller_list(userId);
        log.info(sellerlist.toString());
        return sellerlist;
    }
}
