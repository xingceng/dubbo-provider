package com.jk.dao;

import com.jk.model.BuyHouse;
import com.jk.model.Knowledge;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface KnowDao {
    @Select("select * from z_knowledge where pid=#{pid}")
    List<Knowledge> queryKnowByPid(Integer pid);

    @Insert("insert into z_buyhouse(title,content,kid,cover,dateout) values(#{title},#{content},#{kid},#{cover},sysdate())")
    void addBuyHouse(BuyHouse buyHouse);
}
