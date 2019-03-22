package com.jk.dao;

import com.jk.model.*;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.List;

public interface HouseRwjDao {

    //查询面积
    @Select("select * from t_acreage ")
    List<Acreage> queryAcreage();
        @Select("select * from t_louceng ")
    List<Louceng> queryLouCeng();

    //查询楼龄
    @Select("select * from t_louling ")
    List<Louling> queryLouLing();

    //查询价格
    @Select("select * from t_privce ")
    List<Privace> queryPrice();

    //查询用途
    @Select("select * from t_yongtu ")
    List<Yongtu> queryYongtu();

    //查询装修
    @Select("select * from t_zhuangxiu ")
    List<Zhuangxiu> queryZhuangxiu();

    //查询户型
    @Select("select * from t_unit ")
    List<Unit> queryHuxing();

    //查询朝向
    @Select("select * from t_mianxiang ")
    List<Mianxiang> queryMianxiang();

    //查询二手房源
    @Select("SELECT h.*,a.`acreagename`,c.`circuitname`,lc.`loucengname`,ll.`loulingname`,m.`mianxiangname`,p.`pricename`,u.`unitname`,yt.`yongtuname`,z.`zhuangxiuname` FROM t_house h,t_acreage a,t_louceng lc,t_louling ll,t_mianxiang m,t_privce p,t_unit u,t_yongtu yt,t_zhuangxiu z,t_circuit c where h.houseacreageid = a.`acreageid`and h.`housecircuitid`=c.`circuitid`and h.`houseloucengid`=lc.`loucengid`and h.`houseloulingid`=ll.`loulingid` and h.`housemianxiang`=m.`mianxiangid`and h.`housepriceid` =p.`priceid` and h.`houseunitid`=u.`unitid` and h.`houseyongtuid`=yt.`yongtuid`and h.`housezhuangxiuid`=z.`zhuangxiuid` and h.`housetype`=#{housetype} and h.`housestuts`=0 ORDER BY h.`housecount` DESC limit 0,6")
    List<House> queryfangyuan(Integer housetype);

    @Select("SELECT * FROM t_circuit c WHERE c.`pid`=#{id}")
    List<Circuit> queryxianlu(Integer id);


}
