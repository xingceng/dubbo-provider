/**
 * @ClassName: BroServiceImpl
 * @Description: TODO
 * @Author: 杨明瑞
 * @Date: 2019/3/18 15:53
 * @Version: 1.0
 */
package com.jk.service.impl;

import com.jk.dao.BroDao;
import com.jk.model.Broker;
import com.jk.model.Circuit;
import com.jk.model.House;
import com.jk.model.Store;
import com.jk.service.BroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class BroServiceImpl implements BroService {

    @Autowired
    private BroDao broDao;

    @Override
    public HashMap<String, Object> queryBroker(int page, int rows,Broker bro) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total = broDao.queryTotal(bro);
        int start = (page - 1) * rows;
        List<Broker> list = broDao.queryPage(start,rows,bro);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public void saveBro(Broker broker) {
        Integer id = broker.getId();
        if (id!=null){
            //broDao.updateBro(broker);
        } else {
            broDao.saveBro(broker);
        }
        broDao.saveFoll(broker.getId());
    }

    @Override
    public List<Circuit> queryCirByPid(int pid) {
        return broDao.queryCirByPid(pid);
    }

    @Override
    public List<House> queryHouse(Integer housecircuitid) {
        return broDao.queryHouse(housecircuitid);
    }

    @Override
    public HashMap<String, Object> queryMyBro(int page, int rows, Integer userid) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total = broDao.queryMyBroTotal(userid);
        int start = (page - 1) * rows;
        List<Broker> list = broDao.queryMyBroPage(start,rows,userid);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public List<Store> queryStore(Integer cityid) {
        return broDao.queryStore(cityid);
    }

    @Override
    public List<Broker> queryBroInfo(Integer id) {
        return broDao.queryBroInfo(id);
    }
}
