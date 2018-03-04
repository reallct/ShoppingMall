package com.tangz.shoppingmall.web.controller;

import com.tangz.shoppingmall.meta.Goods;
import com.tangz.shoppingmall.meta.ShoppingCart;
import com.tangz.shoppingmall.meta.User;
import com.tangz.shoppingmall.service.impl.CartServiceImpl;
import com.tangz.shoppingmall.service.impl.GoodsServiceImpl;
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
public class CartController {
    @Autowired
    private CartServiceImpl cartServiceImpl;
    @Autowired
    private GoodsServiceImpl goodsServiceImpl;

    @RequestMapping(value = "/addToMyCart.do")
    @ResponseBody
    public Map<String, Object> addToMyCart(HttpServletRequest request,
                                           @RequestParam("goodsId") int goodsId,
                                           @RequestParam("num") int num,
                                           @RequestParam("userId") int userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getType() == 1) {
            map.put("code", -1);
            return map;
        }

        int success = 0;
        ShoppingCart cart = cartServiceImpl.findByUserId(userId);
        if (cart != null) {
            String[] ids = cart.getGoodsIds().split(",");
            String[] nums = cart.getGoodsNums().split(",");
            if (cart.getGoodsIds().contains(goodsId + "")) {
                String newNums = "";
                for (int i = 0; i < ids.length; i++) {
                    String id = ids[i];
                    if (id.equals(goodsId + "")) {
                        nums[i] = (Integer.valueOf(nums[i]) + num) + "";
                    }
                    newNums = newNums + nums[i] + ",";
                }
                cart.setGoodsNums(newNums);
            } else {
                cart.setGoodsIds(cart.getGoodsIds() + goodsId + ",");
                cart.setGoodsNums(cart.getGoodsNums() + num + ",");
            }
            success = cartServiceImpl.updateCart(cart);
        } else {
            cart = new ShoppingCart();
            cart.setGoodsIds(goodsId + ",");
            cart.setGoodsNums(num + ",");
            cart.setUserId(userId);
            success = cartServiceImpl.insertCart(cart);
        }

        if (success != 0) {
            map.put("code", 200);
            map.put("cart", cart);
        } else {
            map.put("code", 0);
        }

        return map;
    }

    @RequestMapping(value = "/MyCart.do")
    @ResponseBody
    public Map<String, Object> MyCart(HttpServletRequest request,
                                      @RequestParam("userId") int userId) {
        Map<String, Object> map = new HashMap<String, Object>();
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null || user.getType() == 1) {
            map.put("code", -1);
            return map;
        }

        ShoppingCart cart = cartServiceImpl.findByUserId(userId);
        List<Goods> goodsList = new ArrayList<Goods>();
        List<Integer> numList = new ArrayList<Integer>();
        if (cart != null && cart.getGoodsIds() != null && !"".equals(cart.getGoodsIds())) {
            String[] ids = cart.getGoodsIds().split(",");
            String[] nums = cart.getGoodsNums().split(",");
            for (int i = 0; i < ids.length; i++) {
                Integer id = Integer.valueOf(ids[i]);
                goodsList.add(goodsServiceImpl.findById(id));
                numList.add(Integer.valueOf(nums[i]));
            }

        }

        map.put("code", 200);
        map.put("goodsList", goodsList);
        map.put("numList", numList);

        return map;
    }

}
