package com.tangz.shoppingmall.service;

import com.tangz.shoppingmall.meta.Order;

import java.util.List;

public interface OrderService {
    public int addOrder(Order order);

    public String getSellerGoodsIds();

    public String getUserGoodsIds(int userId);

    public int getGoodsSelledNum(int goodsId);

    public int getGoodsUserBuyNum(int goodsId, int userId);

    public Order getOrderByUserIdAndGoodsId(int goodsId, int userId);

    public List<Order> getOrderList(int userId);
}
