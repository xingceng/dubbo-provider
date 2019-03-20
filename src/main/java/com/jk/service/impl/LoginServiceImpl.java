package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.LoginDao;
import com.jk.model.HeTong;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public User queryUserByLoginNumber(String username) {
        return loginDao.queryUserByLoginNumber(username);
    }

    @Override
    public List<Tree> queryNavTree() {
        int pid = 0;
        List<Tree> list = treeNode(pid);
        return list;
    }

    @Override
    public List<Tree> queryNavTreeByUserId(Integer id) {
        return loginDao.queryNavTreeByUserId(id);
    }


    private List<Tree> treeNode(int pid) {
        List<Tree> list = loginDao.queryNavTreeByPid(pid);
        for (Tree navBean : list) {
            Integer id = navBean.getId();
            List<Tree> treeNode = treeNode(id);
            navBean.setChildren(treeNode);
        }
        return list;
    }

    @Override
    public List<HeTong> queryHeTong() {
        return loginDao.queryHeTong();
    }

    @Override
    public int random() {
        return loginDao.random();
    }
}
