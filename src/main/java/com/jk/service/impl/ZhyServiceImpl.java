package com.jk.service.impl;

import com.jk.dao.ZhyDao;
import com.jk.model.*;
import com.jk.service.ZhyService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class ZhyServiceImpl implements ZhyService {

    @Resource
    private ZhyDao zhyDao;


    //查询面积
    @Override
    public HashMap<String, Object> queryAcreage(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryAcreageCount();
        int start=(page-1)*rows;
        List<Acreage> list=zhyDao.queryAcreagePage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    //查询楼层
    @Override
    public HashMap<String, Object> queryLouCeng(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryLoucengCount();
        int start=(page-1)*rows;
        List<Louceng> list=zhyDao.queryLoucengPage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    //查询楼龄
    @Override
    public HashMap<String, Object> queryLouLing(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryLoulingCount();
        int start=(page-1)*rows;
        List<Louling> list=zhyDao.queryLoulingPage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    //查询价格
    @Override
    public HashMap<String, Object> queryPrice(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryPrivaceCount();
        int start=(page-1)*rows;
        List<Privace> list=zhyDao.queryPrivacePage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    //查询用途
    @Override
    public HashMap<String, Object> queryYongtu(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryYongtuCount();
        int start=(page-1)*rows;
        List<Yongtu> list=zhyDao.queryYongtuPage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryZhuangxiu(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryZhuangxiuCount();
        int start=(page-1)*rows;
        List<Zhuangxiu> list=zhyDao.queryZhuangxiuPage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    //查询户型
    @Override
    public HashMap<String, Object> queryHuxing(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryUnitCount();
        int start=(page-1)*rows;
        List<Unit> list=zhyDao.queryUnitPage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryMianxiang(Integer page, Integer rows) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryMianxiangCount();
        int start=(page-1)*rows;
        List<Mianxiang> list=zhyDao.queryMianxiangPage(start,rows);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryFangwu(Integer page, Integer rows,Integer typeid) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =zhyDao.queryHouseCount(typeid);
        int start=(page-1)*rows;
        List<House> list=zhyDao.queryHousePage(start,rows,typeid);
        System.err.print(total);
        System.err.print(start);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    //新增房源
    @Override
    public void addHouse(House house) {
        zhyDao.addHouse(house);
    }
}
