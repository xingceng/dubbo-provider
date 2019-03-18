package com.jk.dao;

import com.jk.model.Tree;
import com.jk.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface LoginDao {

    @Select("select * from t_user u where u.username = #{username}")
    User queryUserByLoginNumber(String username);

    @Select("select n.id,n.text,n.pid,n.url  from t_tree n where n.pid = #{pid}")
    List<Tree> queryNavTreeByPid(int pid);
}
