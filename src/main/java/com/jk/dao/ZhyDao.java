package com.jk.dao;

import com.jk.model.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ZhyDao {

    //查询面积总条数
    @Select("select count(*) from t_acreage ")
    long queryAcreageCount();

    //面积分页查询
    @Select("select * from t_acreage limit #{start},#{rows}")
    List<Acreage> queryAcreagePage(@Param("start") int start, @Param("rows") Integer rows);

    //查询楼层总条数
    @Select("select count(*) from t_louceng ")
    long queryLoucengCount();

    //楼层分页查询
    @Select("select * from t_louceng limit #{start},#{rows}")
    List<Louceng> queryLoucengPage(@Param("start") int start, @Param("rows") Integer rows);

    //查询楼龄总条数
    @Select("select count(*) from t_louling ")
    long queryLoulingCount();

    //楼龄分页查询
    @Select("select * from t_louling limit #{start},#{rows}")
    List<Louling> queryLoulingPage(@Param("start") int start, @Param("rows") Integer rows);

    //查询价格总条数
    @Select("select count(*) from t_privce ")
    long queryPrivaceCount();

    //价格分页查询
    @Select("select * from t_privce limit #{start},#{rows}")
    List<Privace> queryPrivacePage(@Param("start") int start, @Param("rows") Integer rows);

    //查询用途总条数
    @Select("select count(*) from t_yongtu ")
    long queryYongtuCount();

    //价格用途查询
    @Select("select * from t_yongtu limit #{start},#{rows}")
    List<Yongtu> queryYongtuPage(@Param("start") int start, @Param("rows") Integer rows);

    //查询装修总条数
    @Select("select count(*) from t_zhuangxiu ")
    long queryZhuangxiuCount();

    //价格装修查询
    @Select("select * from t_zhuangxiu limit #{start},#{rows}")
    List<Zhuangxiu> queryZhuangxiuPage(@Param("start") int start, @Param("rows") Integer rows);

    //查询户型总条数
    @Select("select count(*) from t_unit ")
    long queryUnitCount();

    //价格户型查询
    @Select("select * from t_unit limit #{start},#{rows}")
    List<Unit> queryUnitPage(@Param("start") int start, @Param("rows") Integer rows);

    //查询朝向总条数
    @Select("select count(*) from t_mianxiang ")
    long queryMianxiangCount();

    //朝向户型查询
    @Select("select * from t_mianxiang limit #{start},#{rows}")
    List<Mianxiang> queryMianxiangPage(@Param("start") int start, @Param("rows") Integer rows);

    //查询房屋个数
    @Select("SELECT count(*) FROM t_house h,t_acreage a,t_louceng lc,t_louling ll,t_mianxiang m,t_privce p,t_unit u,t_yongtu yt,t_zhuangxiu z,t_circuit c where h.houseacreageid = a.`acreageid`and h.`housecircuitid`=c.`circuitid`and h.`houseloucengid`=lc.`loucengid`and h.`houseloulingid`=ll.`loulingid` and h.`housemianxiang`=m.`mianxiangid`and h.`housepriceid` =p.`priceid` and h.`houseunitid`=u.`unitid` and h.`houseyongtuid`=yt.`yongtuid`and h.`housezhuangxiuid`=z.`zhuangxiuid` and h.`housetype`=#{typeid} and h.`housestuts`=0 ")
    long queryHouseCount(Integer typeid);

    //查询房屋分页
    @Select("SELECT h.*,a.`acreagename`," +
            "c.`circuitname`," +
            "lc.`loucengname`," +
            "ll.`loulingname`," +
            "m.`mianxiangname`," +
            "p.`pricename`," +
            "u.`unitname`," +
            "yt.`yongtuname`," +
            "z.`zhuangxiuname` FROM " +
            "t_house h," +
            "t_acreage a," +
            "t_louceng lc," +
            "t_louling ll," +
            "t_mianxiang m," +
            "t_privce p," +
            "t_unit u,t_yongtu yt," +
            "t_zhuangxiu z," +
            "t_circuit c " +
            "where h.houseacreageid = a.`acreageid`" +
            "and h.`housecircuitid`=c.`circuitid`" +
            "and h.`houseloucengid`=lc.`loucengid`" +
            "and h.`houseloulingid`=ll.`loulingid` " +
            "and h.`housemianxiang`=m.`mianxiangid" +
            "`and h.`housepriceid` =p.`priceid` " +
            "and h.`houseunitid`=u.`unitid`" +
            " and h.`houseyongtuid`=yt.`yongtuid`" +
            "and h.`housezhuangxiuid`=z.`zhuangxiuid` " +
            "and h.`housetype`=#{typeid} " +
            "and h.`housestuts`=0" +
            "  limit #{start},#{rows}")
    List<House> queryHousePage(@Param("start") int start, @Param("rows") Integer rows,@Param("typeid") Integer typeid);

    //新增房源
    @Insert("INSERT INTO t_house(housename," +
            "housetype," +
            "housecircuitid," +
            "housepriceid," +
            "houseacreageid," +
            "houseunitid," +
            "houseyongtuid," +
            "houseloucengid," +
            "housemianxiang," +
            "houseloulingid," +
            "housezhuangxiuid," +
            "housestuts,housetime) VALUES(#{housename}," +
            "#{housetype}," +
            "#{housecircuitid}," +
            "#{housepriceid}," +
            "#{houseacreageid}," +
            "#{houseunitid}," +
            "#{houseyongtuid}," +
            "#{houseloucengid}," +
            "#{housemianxiang}," +
            "#{houseloulingid}," +
            "#{housezhuangxiuid}," +
            "0," +
            "SYSDATE())")
    void addHouse(House house);
}
