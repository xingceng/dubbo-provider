package com.jk.dao;

import com.jk.model.*;
import org.apache.ibatis.annotations.*;


import java.util.List;

public interface ZylDao {


    //网站用户看房
    @Select("<script>"
            + "select count(*) from z_userapply ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id "
            + " WHERE 1=1"
            + "<if test='u.name!=null'>"
            + "and ua.name like '%${u.name}%'"
            + "</if>"
            + "</script>")
    long queryUserApplyCount(@Param("u")UserApply u);
    @Select("<script>"
            + "select count(*) from z_userapply ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id "
            + " WHERE 1=1 and status=0 "
            + "<if test='u.name!=null'>"
            + "and ua.name like '%${u.name}%'"
            + "</if>"
            + "</script>")
    long queryUserApplyCount0(@Param("u")UserApply u);
    @Select("<script>"
            + "select count(*) from z_userapply ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id "
            + " WHERE 1=1 and status=1 "
            + "<if test='u.name!=null'>"
            + "and ua.name like '%${u.name}%'"
            + "</if>"
            + "</script>")
    long queryUserApplyCount1(@Param("u")UserApply u);

    @Select("<script>"
            + "select ua.*,h.houseid,h.housename,b.username from z_userapply ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id  "
            + "  WHERE 1=1"
            + "<if test='u.name!=null'>"
            + "and ua.name like '%${u.name}%'"
            + "</if>"
            + "LIMIT #{start},#{rows}"
            + "</script>")
    List<UserApply> queryBlogPage(@Param("start") int start, @Param("rows") int rows,@Param("u") UserApply u);
    @Select("<script>"
            + "select ua.*,h.houseid,h.housename,b.username from z_userapply ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id  "
            + "  WHERE 1=1 and status=0 "
            + "<if test='u.name!=null'>"
            + "and ua.name like '%${u.name}%'"
            + "</if>"
            + "LIMIT #{start},#{rows}"
            + "</script>")
    List<UserApply> queryBlogPage0(@Param("start") int start, @Param("rows") int rows,@Param("u") UserApply u);
    @Select("<script>"
            + "select ua.*,h.houseid,h.housename,b.username from z_userapply ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id  "
            + "  WHERE 1=1 and status=1 "
            + "<if test='u.name!=null'>"
            + "and ua.name like '%${u.name}%'"
            + "</if>"
            + "LIMIT #{start},#{rows}"
            + "</script>")
    List<UserApply> queryBlogPage1(@Param("start") int start, @Param("rows") int rows,@Param("u") UserApply u);
    //新增  用户看房
    @Insert("insert into z_userapply(name,phone,hid,applytime,looktime,status) VALUES(#{name},#{phone},#{hid},#{applytime},#{looktime},0)")
    void addUserApply(UserApply u);
    //修改  用户看房
    @Update("update z_userapply set name=#{name},phone=#{phone},hid=#{hid},applytime=#{applytime},looktime=#{looktime} where id=#{id}")
    void updateUserApply(UserApply u);
    //回显  用户看房
    @Select("select * from z_userapply where id = #{id}")
    UserApply queryUserApplyById(Integer id);
    //删除  用户看房
    @Delete("delete from z_userapply where id =#{ids}")
    void deleteUserApply(String ids);

    @Select("select * from t_house")
    List<House> queryHouse();

    @Select("select u.id,u.username from t_user u,t_role r where u.roleid=r.id and r.id=2")
    List<User> queryUser();

    //申请看房  修改状态
    //改为 0 1
    @Update("update z_userapply set status=0 where id=#{id}" )
    void updateApplyStatus0(Integer id);
    @Update("update z_userapply set status=1 where id=#{id}" )
    void updateApplyStatus1(Integer id);

    @Update("update z_userapply set brokerid=#{brid} where id=#{id}")
    void updateBro(@Param("id") Integer id,@Param("brid") Integer brid);
//==========================================================================================
    //看房记录
    @Select("<script>"
        + "select count(*) from z_lookhouse ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id "
        + " WHERE 1=1"
        + "<if test='l.phone!=null'>"
        + "and ua.phone like '%${l.phone}%'"
        + "</if>"
        + "</script>")
    long queryLookHouseCount(@Param("l") LookHouse l);

    @Select("<script>"
            + "select ua.*,h.houseid,h.housename,b.username from z_lookhouse ua left join t_house h on ua.hid=h.houseid left join t_user b on ua.brokerid=b.id  "
            + " WHERE 1=1"
            + "<if test='l.phone!=null'>"
            + "and ua.phone like '%${l.phone}%'"
            + "</if>"
            + "LIMIT #{start},#{rows}"
            + "</script>")
    List<LookHouse> queryLookHouse(@Param("start")int start, @Param("rows")Integer rows, @Param("l")LookHouse l);

    @Insert("insert into z_lookhouse(looktime,hid,phone,feedback,commit,brokerid) VALUES(#{looktime},#{hid},#{phone},#{feedback},#{commit},#{brokerid})")
    void addLookHouse(LookHouse l);

    @Select("select  * from z_lookhouse where id =#{id}  ")
    LookHouse queryLookHouseById(Integer id);

    @Update("update z_lookhouse set looktime=#{looktime},hid=#{hid},phone=#{phone},feedback=#{feedback},commit=#{commit},brokerid=#{brokerid} where id =#{id}")
    void updateLookHouse(LookHouse l);

    @Delete("delete from z_lookhouse where id=#{ids}")
    void deleteLookHouse(String ids);

    @Select("<script>"
            + "select count(*) from z_buyhouse b left join z_knowledge k on b.kid=k.id "
            + " WHERE 1=1"
            + "<if test='b.title!=null'>"
            + "and b.title = '${b.title}%'"
            + "</if>"
            + "</script>")
    long queryBuycount(@Param("b") BuyHouse b);
    @Select("<script>"
            + "select * from z_buyhouse b left join z_knowledge k on b.kid=k.id "
            + " WHERE 1=1"
            + "<if test='b.title!=null'>"
            + "and b.title = '${b.title}%'"
            + "</if>"
            +"LIMIT #{start},#{rows}"
            + "</script>")
    List<BuyHouse> queryBuyHouse(@Param("start")int start, @Param("rows")Integer rows, @Param("b")BuyHouse b);

    @Delete("delete from z_buyhouse where id=#{ids}")
    void deleteBuyHouse(String ids);
    @Select("select * from z_knowledge")
    List<Knowledge> queryKnowledge();
    @Update("update z_userapply set static=1 where id=#{id}")
    void updateUser(Integer id);
    @Select("select h1.houseid,h1.housename from t_house h1,(select u.hid from z_userapply u where  u.hid not in(select h.hid from z_hetong h where h.status != 1) and u.brokerid=#{roleid} and u.status=1)h2 where h1.houseid=h2.hid")
    List<House> queryUserApplyHouse(Integer roleid);
}
