package com.tangz.shoppingmall.service.impl;

import com.tangz.shoppingmall.dao.GoodsDao;
import com.tangz.shoppingmall.meta.Goods;
import com.tangz.shoppingmall.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    public int insertGoods(Goods goods) {
        return goodsDao.insertGoods(goods);
    }

    public Goods findById(int id) {
        return goodsDao.findById(id);
    }

    public int updateGoods(Goods goods) {
        return goodsDao.updateGoods(goods);
    }

    public int deleteGoods(int id) {
        return goodsDao.deleteGoods(id);
    }

    public List<Goods> getGoodsList() {
        return goodsDao.getGoodsList();
    }

}
