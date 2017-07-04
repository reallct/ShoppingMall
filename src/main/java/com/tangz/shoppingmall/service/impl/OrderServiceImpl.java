package com.tangz.shoppingmall.service.impl;

import com.tangz.shoppingmall.dao.OrderDao;
import com.tangz.shoppingmall.meta.Order;
import com.tangz.shoppingmall.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;

    public int addOrder(Order order) {
        return orderDao.insertOrder(order);
    }

    public String getSellerGoodsIds() {
        List<Integer> list = orderDao.getAllOrderGoodsId();
        String ans = "";
        if (list != null && list.size() > 0) {
            for (Integer goodsId : list) {
                ans = ans + goodsId + ",";
            }
            ans.substring(0, ans.length() - 2);
        }
        return ans;
    }

    public String getUserGoodsIds(int userId) {
        List<Integer> list = orderDao.getAllGoodsIdForUser(userId);
        String ans = "";
        if (list != null && list.size() > 0) {
            for (Integer goodsId : list) {
                ans = ans + goodsId + ",";
            }
            ans.substring(0, ans.length() - 1);
        }
        return ans;
    }

    public int getGoodsSelledNum(int goodsId) {
        int num = orderDao.getGoodsSelledNum(goodsId);
        return num;
    }

    public int getGoodsUserBuyNum(int goodsId, int userId) {
        int num = orderDao.getGoodsUserBuyNum(goodsId, userId);
        return num;
    }

    public Order getOrderByUserIdAndGoodsId(int goodsId, int userId) {
        Order order = orderDao.getOrderByUserIdAndGoodsId(goodsId, userId);
        return order;
    }

    public List<Order> getOrderList(int userId) {
        List<Order> list = orderDao.getOrderList(userId);
        return list;
    }

}
