package com.jk.dao;

import com.jk.model.HeTong;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface HeDao {
    @Select("<script>"
            + "select count(*) from z_hetong h,t_house h1,t_broker b where h.hid=h1.houseid and h.brokerid=b.id  "
            + " and  1=1"
            + "<if test='heTong.email!=null'>"
            + "and h.email like '%${heTong.email}%'"
            + "</if>"
            + "</script>")
    long queryHeCount(@Param("heTong") HeTong heTong);

    @Select("<script>"
            + "select h.id,h.phone,h.email,h.card,h.cardimg,h.price,h.deadline,h.comment,h.latedate,h1.housename hname,b.broName brokername,h.status from z_hetong h,t_house h1,t_broker b where h.hid=h1.houseid and h.brokerid=b.id  "
            + " and  1=1"
            + "<if test='heTong.email!=null'>"
            + "and h.email like '%${heTong.email}%'"
            + "</if>"
            +"LIMIT #{start},#{rows}"
            + "</script>")
    List<HeTong> queryHeTong(@Param("start") int start,@Param("rows") Integer rows,@Param("heTong") HeTong heTong);

    @Delete("delete from  z_hetong where id=#{id}")
    void delheTong(Integer id);
}
