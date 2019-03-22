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
            "select b.id,b.broCover,b.broName,b.iphone,b.workYear," +
            "count(f.userid) follCount,concat(c.circuitname,'-',c2.circuitname) coverage," +
            "count(l.id) daikan,count(h.id) zuping from t_follow f " +
            "left join t_broker b on f.broid = b.id " +
            "left join t_circuit c on b.province = c.circuitid " +
            "left join t_circuit c2 on b.city = c2.circuitid " +
            "left join z_lookhouse l on b.id = l.brokerid and l.`commit` = 1 " +
            "left join z_hetong h on b.id = h.brokerid and h.`status` = 0 " +
            "where 1 = 1 " +
            "<if test='bro.province != null'> " +
            "and b.province = '${bro.province}' " +
            "and b.city = '${bro.city}' " +
            "</if> " +
            "group by f.broid limit #{start},#{rows} " +
            "</script>")
    List<Broker> queryPage(@Param("start") int start, @Param("rows") int rows,@Param("bro") Broker bro);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into t_broker(broname,brocover,iphone,province,city) values(#{broName},#{broCover},#{iphone},#{province},#{city})")
    void saveBro(Broker broker);

    @Select("select * from t_circuit where pid = #{pid}")
    List<Circuit> queryCirByPid(int pid);

    @Insert("insert into t_follow(broid,userid) values(#{broid},0)")
    void saveFoll(@Param("broid") Integer id);
}
