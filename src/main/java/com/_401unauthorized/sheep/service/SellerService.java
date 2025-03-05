package com._401unauthorized.sheep.service;

import com._401unauthorized.sheep.dao.SellerDao;
import com._401unauthorized.sheep.dto.TakeoffDto;
import com._401unauthorized.sheep.dto.WishDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class SellerService {
    private final SellerDao sellerDao;

    public List<WishDto> seller_list(String userId) {
        List<WishDto> sellerlist = sellerDao.seller_list(userId);
        for (WishDto seller : sellerlist) {
            log.info(seller.toString());
        }
        return sellerlist;
    }

}
