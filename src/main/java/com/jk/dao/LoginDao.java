package com.jk.dao;

import com.jk.model.HeTong;
import com.jk.model.Tree;
import com.jk.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoginDao {

    @Select("select * from t_user u where u.username = #{username}")
    User queryUserByLoginNumber(String username);

    @Select("select n.id,n.text,n.pid,n.url  from t_tree n where n.pid = #{pid}")
    List<Tree> queryNavTreeByPid(int pid);
    @Select("SELECT * FROM  t_user u WHERE u.`username`=#{username}")
    List<User> getTestNameIsNull(User user);

    @Select("select * from z_hetong")
    List<HeTong> queryHeTong();
    @Select("SELECT u.id FROM t_user u where u.roleid=2 ORDER BY  RAND() LIMIT 1")
    int random();

    @Select("select distinct(n.id),n.text,n.url,n.pid from t_tree n left join t_role_power rp on n.id = rp.power_id left join t_user_role ur on rp.role_id = ur.roleid where ur.userid = #{id}")
    List<Tree> queryNavTreeByUserId(Integer id);
}
