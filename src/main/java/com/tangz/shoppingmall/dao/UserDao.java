package com.tangz.shoppingmall.dao;

import com.tangz.shoppingmall.meta.User;
import org.apache.ibatis.annotations.Select;

public interface UserDao {
    @Select("select * from user where id=#{id}")
    public User findById(int id);

    @Select("select * from user where name=#{name}")
    public User findByName(String name);
}
