package com.jk.dao;

import com.jk.model.PowerMenu;
import com.jk.model.Role;
import com.jk.model.Tree;
import com.jk.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserDao {
    @Select("select count(*) from t_user ")
    long queryUserCount();

    @Select("select * from t_user u,t_role r where u.roleid=r.id limit #{start},#{rows}")
    List<User> queryUserPage(@Param("start")int start, @Param("rows")Integer rows);

    @Update("update t_user u set u.password=#{password} where u.id=#{uid}")
    void updatPwd(User user);

    @Select("select * from t_role")
    List<Role> queryRoleList();

    @Select("select n.* from t_tree n left join t_role_power rp on n.id = rp.power_id where rp.role_id = #{roleid}")
    List<Tree> queryPowerByRoleId(Integer roleid);

    @Select("select n.id,n.text,n.pid,n.url from t_tree n")
    List<Tree> queryNavTree();

    @Delete("delete from t_role_power where role_id = #{roleId}")
    void deleteRolePowerByRoleId(Integer roleId);

    @Insert("insert into t_role_power(role_id,power_id) values(#{roleId},#{powerId})")
    void saveRolePower(@Param("roleId") Integer roleId, @Param("powerId")Integer powerId);

    @Select("select m.id,m.name,m.url,m.powerid powerId from t_power_menu m where m.powerid = #{powerId}")
    List<PowerMenu> queryPowerMenuList(Integer powerId);

    @Insert("insert into t_power_menu(name,url,powerid) values(#{name},#{url},#{powerId})")
    void savePowerMenu(PowerMenu menu);

    @Insert("insert into h_guanzhu(userid,houseid) value(#{userid},#{houseid})")
    void guanZhu(@Param("houseid") Integer houseid,@Param("userid") Integer userid);
}
