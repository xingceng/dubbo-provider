package com.jk.service;

import com.jk.model.HeTong;
import com.jk.model.Tree;
import com.jk.model.User;

import java.util.List;

public interface LoginService {

    User queryUserByLoginNumber(String username);

    List<Tree> queryNavTree();

    List<HeTong> queryHeTong();
    //随机取出一条客服人员id
    int random();
}
