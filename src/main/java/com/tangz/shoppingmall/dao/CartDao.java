package com.tangz.shoppingmall.dao;

import com.tangz.shoppingmall.meta.ShoppingCart;
import org.apache.ibatis.annotations.*;


public interface CartDao {
    @Insert("INSERT INTO shoppingcart (userId, goodsIds, goodsNums, createTime, updateTime) VALUES (#{userId}, #{goodsIds}, #{goodsNums}, NOW(), NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertCart(ShoppingCart shoppingCart);

    @Select("select * from shoppingcart where userId=#{userId}")
    public ShoppingCart findByUserId(@Param("userId") Integer userId);

    @Update("update shoppingcart set goodsIds=#{goodsIds}, goodsNums=#{goodsNums}, updateTime=NOW()"
            + " where userId=#{userId}")
    public int updateCart(ShoppingCart cart);
}
