package com.tangz.shoppingmall.service;

import com.tangz.shoppingmall.meta.Goods;

import java.util.List;


public interface GoodsService {
    public int insertGoods(Goods goods);

    public Goods findById(int id);

    public int updateGoods(Goods goods);

    public int deleteGoods(int id);

    public List<Goods> getGoodsList();
}
