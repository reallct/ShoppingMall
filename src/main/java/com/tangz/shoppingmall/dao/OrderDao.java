package com.tangz.shoppingmall.dao;

import com.tangz.shoppingmall.meta.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface OrderDao {

    @Insert("INSERT INTO myorder (userId, goodsId, num, goodsPrice, totalPrice, createTime) " +
            "VALUES (#{userId}, #{goodsId}, #{num}, #{goodsPrice}, #{totalPrice}, NOW())")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertOrder(Order order);

    @Select("select * from myorder where goodsId = #{goodsId} and userId = #{userId}")
    public Order getOrderByUserIdAndGoodsId(@Param("goodsId") int goodsId, @Param("userId") int userId);

    @Select("select * from myorder where userId=#{userId}")
    public List<Order> getOrderList(int userId);

    @Select("select goodsId from myorder")
    public List<Integer> getAllOrderGoodsId();

    @Select("select goodsId from myorder where userId=#{userId}")
    public List<Integer> getAllGoodsIdForUser(int userId);

    @Select("select IFNULL(sum(num),0) from myorder where goodsId = #{goodsId}")
    public int getGoodsSelledNum(int goodsId);

    @Select("select IFNULL(count(num),0) from myorder where goodsId = #{goodsId} and userId = #{userId}")
    public int getGoodsUserBuyNum(@Param("goodsId") int goodsId, @Param("userId") int userId);

}
