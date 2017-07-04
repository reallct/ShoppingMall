package com.tangz.shoppingmall.dao;

import com.tangz.shoppingmall.meta.Goods;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;


public interface GoodsDao {

    @Insert("INSERT INTO goods (name, summary, price, picUrl, detail, createTime, updateTime, status) " +
            "VALUES (#{name}, #{summary}, #{price}, #{picUrl}, #{detail}, NOW(), NOW(), #{status} )")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    public int insertGoods(Goods goods);

    @Select("select * from goods where id=#{id} and status=1")
    public Goods findById(int id);

    @Update("update goods set name=#{name}, summary=#{summary}, price=#{price}, picUrl=#{picUrl}, detail=#{detail}, status=#{status}, updateTime=NOW()"
            + " where id=#{id}")
    public int updateGoods(Goods goods);

    @Update("update goods set status=0" + " where id=#{id}")
    public int deleteGoods(int id);

    @Select("select id, name, price, picUrl from goods where status=1")
    public List<Goods> getGoodsList();
}
