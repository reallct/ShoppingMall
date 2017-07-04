package com.tangz.shoppingmall.web.controller;

import com.tangz.shoppingmall.meta.Goods;
import com.tangz.shoppingmall.meta.User;
import com.tangz.shoppingmall.meta.Order;
import com.tangz.shoppingmall.meta.ShoppingCart;
import com.tangz.shoppingmall.service.impl.CartServiceImpl;
import com.tangz.shoppingmall.service.impl.GoodsServiceImpl;
import com.tangz.shoppingmall.service.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class OrderController {
    @Autowired
    private OrderServiceImpl orderServiceImpl;
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @RequestMapping(value = "selledGoodsIds.do")
    @ResponseBody
    public Map<String, Object> selledGoodsIds() {
        Map<String, Object> map = new HashMap<String, Object>();
        String goodsIds = orderServiceImpl.getSellerGoodsIds();

        map.put("goodsIds", goodsIds);
        map.put("code", 200);

        return map;
    }

    @RequestMapping(value = "userGoodsIds.do")
    @ResponseBody
    public Map<String, Object> userGoodsIds(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();
        String goodsIds = orderServiceImpl.getUserGoodsIds(user.getId());

        map.put("goodsIds", goodsIds);
        map.put("code", 200);

        return map;
    }

    @RequestMapping(value = "buyGoods.do")
    @ResponseBody
    public Map<String, Object> buyGoods(HttpServletRequest request,
                                        @RequestParam("nums") String nums,
                                        @RequestParam("goodsIds") String goodsIds,
                                        @RequestParam("prices") String prices) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();

        String[] numss = nums.split(",");
        String[] goodsIdss = goodsIds.split(",");
        String[] pricess = prices.split(",");
        int success = 0;
        for (int i = 0; i < numss.length; i++) {
            if (!"0".equals(numss[i])) {
                Order order = new Order();
                order.setUserId(user.getId());
                order.setGoodsId(Integer.valueOf(goodsIdss[i]));
                order.setNum(Integer.valueOf(numss[i]));
                order.setGoodsPrice(Double.valueOf(pricess[i]));
                order.setTotalPrice(Integer.valueOf(numss[i]) * Double.valueOf(pricess[i]));
                success = orderServiceImpl.addOrder(order);
            }
        }

        if (success != 0) {
            ShoppingCart cart = cartServiceImpl.findByUserId(user.getId());
            cart.setGoodsIds("");
            cart.setGoodsNums("");
            cartServiceImpl.updateCart(cart);
            map.put("code", 200);
        } else {
            map.put("code", 0);
        }

        return map;
    }

    @RequestMapping(value = "myOrders.do")
    @ResponseBody
    public Map<String, Object> myOrders(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<String, Object> map = new HashMap<String, Object>();

        List<Order> list = orderServiceImpl.getOrderList(user.getId());
        List<Goods> goodsList = new ArrayList<Goods>();
        if (list != null && !list.isEmpty()) {
            for (Order order : list)
                goodsList.add(goodsServiceImpl.findById(order.getGoodsId()));
        }

        map.put("orderList", list);
        map.put("goodsList", goodsList);
        map.put("code", 200);

        return map;
    }
}
