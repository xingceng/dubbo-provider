/**
 * @ClassName: BroDao
 * @Description: TODO
 * @Author: 杨明瑞
 * @Date: 2019/3/18 16:00
 * @Version: 1.0
 */
package com.jk.dao;

import com.jk.model.Broker;
import com.jk.model.Circuit;
import com.jk.model.House;
import com.jk.model.Store;
import org.apache.ibatis.annotations.*;
;

import java.util.List;

public interface BroDao {

    /**
     * @Author 杨明瑞
     * @Description //TODO 查询经纪人总条数
     * @Date 16:00 2019/3/18
     * @Param []
     * @return long
     **/
    @Select("<script> " +
            "select count(*) from t_broker b " +
            "where 1 = 1 " +
            "<if test='bro.province != null'> " +
            "and b.province = '${bro.province}' " +
            "and b.city = '${bro.city}' " +
            "</if> " +
            "</script>")
    long queryTotal(@Param("bro") Broker bro);

    /**
     * @Author 杨明瑞
     * @Description //TODO 经纪人分页查询
     * @Date 16:01 2019/3/18
     * @Param [start, rows]
     * @return java.util.List<Broker>
     **/
    @Select("<script> " +
            "select b.id,b.broCover,b.broName,b.iphone,count(f.userid) follCount," +
            "CONCAT(c.circuitname,' · ',c2.circuitname,' · ',s.storename,' · ',b.workyear) coverage," +
            "(select count(l.id) from z_lookhouse l where l.brokerid = b.id and l.`commit` = 1) daikan," +
            "(select count(h.id) from z_hetong h where h.brokerid = b.id and h.`status` = 0) zuping " +
            "from t_follow f " +
            "left join t_broker b on f.broid = b.id " +
            "left join t_circuit c on b.province = c.circuitid " +
            "left join t_circuit c2 on b.city = c2.circuitid " +
            "left join t_store s on b.brostore = s.id " +
            "where 1 = 1 " +
            "<if test='bro.province != null'> " +
            "and b.province = '${bro.province}' " +
            "and b.city = '${bro.city}' " +
            "</if> " +
            "group by f.broid order by count(f.userid) desc limit #{start},#{rows} " +
            "</script>")
    List<Broker> queryPage(@Param("start") int start, @Param("rows") int rows,@Param("bro") Broker bro);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into t_broker(broname,brocover,iphone,province,city,workyear,brostore) values(#{broName},#{broCover},#{iphone},#{province},#{city},#{workYear}'年',#{broStore})")
    void saveBro(Broker broker);

    @Select("select * from t_circuit where pid = #{pid}")
    List<Circuit> queryCirByPid(int pid);

    @Insert("insert into t_follow(broid,userid) values(#{broid},0)")
    void saveFoll(@Param("broid") Integer id);

    @Select("select * from t_house where housecircuitid = #{housecircuitid}")
    List<House> queryHouse(Integer housecircuitid);

    @Select("select count(*) from t_user u where u.id = #{userid}")
    long queryMyBroTotal(@Param("userid") Integer userid);

    @Select("select b.id,b.broCover,b.broName,b.iphone," +
            "CONCAT(c.circuitname,' · ',c2.circuitname,' · ',s.storename,' · ',b.workyear) coverage ," +
            "(select count(l.id) from z_lookhouse l where l.brokerid = b.id and l.`commit` = 1) daikan," +
            "(select count(h.id) from z_hetong h where h.brokerid = b.id and h.`status` = 0) zuping " +
            "from t_kehu k " +
            "left join t_follow f on k.kehuid = f.userid " +
            "left join t_broker b on f.broid = b.id " +
            "left join t_circuit c on b.province = c.circuitid " +
            "left join t_circuit c2 on b.city = c2.circuitid " +
            "left join t_store s on b.brostore = s.id " +
            "where k.kehuid = #{userid} " +
            "group by b.id limit #{start},#{rows}")
    List<Broker> queryMyBroPage(@Param("start") int start, @Param("rows") int rows,@Param("userid") Integer userid);

    @Select("select * from t_store where cityid = #{cityid}")
    List<Store> queryStore(Integer cityid);

    @Select("select b.id,b.brocover,b.broname,b.iphone,b.workyear," +
            "concat(c.circuitname,'-',c2.circuitname) coverage,s.storename," +
            "(select count(f.userid) from t_follow f where f.broid = b.id) follCount," +
            "(select count(l.id) from z_lookhouse l where l.brokerid = b.id and l.`commit` = 1) daikan," +
            "(select count(h.id) from z_hetong h where h.brokerid = b.id and h.`status` = 0) zuping " +
            "from t_follow f left join t_broker b on f.broid = b.id " +
            "left join t_circuit c on b.province = c.circuitid " +
            "left join t_circuit c2 on b.city = c2.circuitid " +
            "left join t_store s on b.brostore = s.id " +
            "where b.id = #{id} " +
            "group by b.id")
    List<Broker> queryBroInfo(Integer id);
}
