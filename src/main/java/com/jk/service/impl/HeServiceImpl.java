package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.HeDao;
import com.jk.model.HeTong;
import com.jk.service.HeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

@Service
public class HeServiceImpl implements HeService {

    @Autowired
    private HeDao heDao;

    /**
     *   合同后台分页查询
     * @param page
     * @param rows
     * @param heTong
     * @return
     */
    @Override
    public HashMap<String, Object> queryHeTong(Integer page,Integer rows,HeTong heTong) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total=heDao.queryHeCount(heTong);
        int start=(page-1)*rows;
        List<HeTong> list=heDao.queryHeTong(start,rows,heTong);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    /**
     * 合同删除
     * @param id
     */
    @Override
    public void delheTong(Integer id) {
        heDao.delheTong(id);
    }
}
