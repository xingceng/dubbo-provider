package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.ZylDao;
import com.jk.model.Broker;
import com.jk.model.House;
import com.jk.model.LookHouse;
import com.jk.model.UserApply;
import com.jk.service.ZylService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

@Service
public class ZylServiceImpl implements ZylService {

    @Autowired
    private ZylDao dao;
    //========================================================================================
    @Override
    public HashMap<String, Object> queryUserApply(Integer rows, Integer page, UserApply u) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =dao.queryUserApplyCount(u);
        int start=(page-1)*rows;
        List<UserApply> list=dao.queryBlogPage(start,rows,u);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }
    @Override
    public HashMap<String, Object> queryUserApply0(Integer rows, Integer page, UserApply u) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =dao.queryUserApplyCount0(u);
        int start=(page-1)*rows;
        List<UserApply> list=dao.queryBlogPage0(start,rows,u);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }
    @Override
    public HashMap<String, Object> queryUserApply1(Integer rows, Integer page, UserApply u) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total =dao.queryUserApplyCount1(u);
        int start=(page-1)*rows;
        List<UserApply> list=dao.queryBlogPage1(start,rows,u);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public void addUserApply(UserApply u) {
        Integer id = u.getId();
        if(id !=null){
            dao.updateUserApply(u);
        }else{
            dao.addUserApply(u);
        }
    }

    @Override
    public UserApply queryUserApplyById(Integer id) {
        return dao.queryUserApplyById(id);
    }

    @Override
    public void deleteUserApply(String ids) {
        dao.deleteUserApply(ids);
    }

    @Override
    public List<House> queryHouse() {
        return dao.queryHouse();
    }

    @Override
    public List<Broker> queryBroker() {
        return dao.queryBroker();
    }

    @Override
    public void updateApplyStatus0(Integer id) {
        dao.updateApplyStatus0(id);
    }

    @Override
    public void updateApplyStatus1(Integer id) {
        dao.updateApplyStatus1(id);
    }

    @Override
    public void updateBro(Integer id, Integer brid) {
        dao.updateBro(id,brid);
    }

    //========================================================================================
    @Override
    public HashMap<String, Object> queryLookHouse(Integer rows, Integer page, LookHouse l) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total=dao.queryLookHouseCount(l);
        int  start=(page-1)*rows;
        List<LookHouse> list=dao.queryLookHouse(start,rows,l);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public void addLookHouse(LookHouse l) {
        Integer id = l.getId();
        if(id!=null){
            dao.updateLookHouse(l);
        }else{
            dao.addLookHouse(l);
        }
    }

    @Override
    public LookHouse queryLookHouseById(Integer id) {

        return dao.queryLookHouseById(id);
    }

    @Override
    public void deleteLookHouse(String ids) {
        dao.deleteLookHouse(ids);
    }


}
