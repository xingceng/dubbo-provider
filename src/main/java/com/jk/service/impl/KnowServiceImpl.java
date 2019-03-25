package com.jk.service.impl;

import com.jk.dao.KnowDao;
import com.jk.model.BuyHouse;
import com.jk.model.Knowledge;
import com.jk.service.KnowService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KnowServiceImpl implements KnowService {
    @Autowired
    private KnowDao knowDao;

    @Override
    public List<Knowledge> queryKnowByPid(Integer pid) {
        return knowDao.queryKnowByPid(pid);
    }

    @Override
    public void addBuyHouse(BuyHouse buyHouse) {
        knowDao.addBuyHouse(buyHouse);
    }
}
