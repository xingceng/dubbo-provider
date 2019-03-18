package com.jk.service.impl;

import com.jk.dao.HouseRwjDao;
import com.jk.model.*;
import com.jk.service.HouseRwjService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class HouseRwjServiceImpl implements HouseRwjService {

    @Resource
    private HouseRwjDao houseRwjDao;



    @Override
    public List<Acreage> queryAcreage() {
        return houseRwjDao.queryAcreage();
    }

    @Override
    public List<Louceng> queryLouCeng() {
        return houseRwjDao.queryLouCeng();
    }

    @Override
    public List<Louling> queryLouLing() {
        return houseRwjDao.queryLouLing();
    }

    @Override
    public List<Privace> queryPrice() {
        return houseRwjDao.queryPrice();
    }

    @Override
    public List<Yongtu> queryYongtu() {
        return houseRwjDao.queryYongtu();
    }

    @Override
    public List<Zhuangxiu> queryZhuangxiu() {
        return houseRwjDao.queryZhuangxiu();
    }

    @Override
    public List<Unit> queryHuxing() {
        return houseRwjDao.queryHuxing();
    }

    @Override
    public List<Mianxiang> queryMianxiang() {
        return houseRwjDao.queryMianxiang();
    }

    @Override
    public List<House> queryfangyuan(Integer housetype) {
        return houseRwjDao.queryfangyuan(housetype);
    }

    @Override
    public List<Circuit> queryxianlu(Integer id) {
        return houseRwjDao.queryxianlu(id);
    }

}
