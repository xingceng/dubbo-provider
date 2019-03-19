package com.jk.service.impl;

import com.jk.dao.UserDao;
import com.jk.model.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public HashMap<String, Object> queryUser(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =userDao.queryUserCount();
        int start=(page-1)*rows;
        List<User> list=userDao.queryUserPage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public void updatPwd(User user) {
        userDao.updatPwd(user);
    }


}
