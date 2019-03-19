package com.jk.dao;

import com.jk.model.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserDao {
    @Select("select count(*) from t_user ")
    long queryUserCount();

    @Select("select * from t_user u,t_role r where u.roleid=r.id limit #{start},#{rows}")
    List<User> queryUserPage(@Param("start")int start, @Param("rows")Integer rows);

    @Update("update t_user u set u.password=#{password} where u.id=#{uid}")
    void updatPwd(User user);
}
