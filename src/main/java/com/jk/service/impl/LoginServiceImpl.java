package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.LoginDao;
import com.jk.model.User;
import com.jk.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginDao loginDao;

    @Override
    public String login(User user) {
        List<User> list = loginDao.getTestNameIsNull(user);
        if (list.size() == 1) {
            if (user.getPassword().equals(list.get(0).getPassword())) {
                return "loginSuccess";
            } else {
                return "userPassNo";
            }
        } else {
            return "userNameNO";
        }
    }
}
