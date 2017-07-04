package com.tangz.shoppingmall.service;

import com.tangz.shoppingmall.meta.ShoppingCart;

public interface CartService {
    public int insertCart(ShoppingCart cart);

    public ShoppingCart findByUserId(int userId);

    public int updateCart(ShoppingCart cart);

}
