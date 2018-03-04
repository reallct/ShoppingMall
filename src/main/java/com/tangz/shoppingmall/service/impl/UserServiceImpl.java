package com.tangz.shoppingmall.service.impl;

import com.tangz.shoppingmall.dao.UserDao;
import com.tangz.shoppingmall.meta.User;
import com.tangz.shoppingmall.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User findById(int id) {
        return userDao.findById(id);
    }

    @Override
    public int checkoutUser(String name, String password) {
        User user = userDao.findByName(name);
        if (user == null || !password.equals(user.getPassword()))
            return 0;

        return user.getId();
    }

    public User findByName(String name) {
        return userDao.findByName(name);
    }

}
