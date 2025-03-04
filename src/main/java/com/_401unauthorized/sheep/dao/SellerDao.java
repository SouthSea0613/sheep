package com._401unauthorized.sheep.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerDao {
    List<String> seller_list(String userId);
}
