package com.tangz.shoppingmall.service;

import com.tangz.shoppingmall.meta.User;

public interface UserService {
    public User findById(int id);

    public User findByName(String name);

    public int checkoutUser(String name, String password);
}
