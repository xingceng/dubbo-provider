/**
 * @ClassName: BroService
 * @Description: TODO
 * @Author: 杨明瑞
 * @Date: 2019/3/18 15:51
 * @Version: 1.0
 */
package com.jk.service;

import com.jk.model.Broker;
import com.jk.model.Circuit;
import com.jk.model.House;
import com.jk.model.Store;

import java.util.HashMap;
import java.util.List;

public interface BroService {

    /**
     * @Author 杨明瑞
     * @Description //TODO 查询经纪人列表
     * @Date 15:52 2019/3/18
     * @Param [page, rows]
     * @return HashMap<String,Object>
     **/
    HashMap<String, Object> queryBroker(int page, int rows,Broker bro);

    /**
     * @Author 杨明瑞
     * @Description //TODO 新增经纪人
     * @Date 13:42 2019/3/19
     * @Param []
     * @return void
     **/
    void saveBro(Broker broker);

    /**
     * @Author 杨明瑞
     * @Description //TODO 查询地区
     * @Date 14:21 2019/3/19
     * @Param []
     * @return java.util.List<com.jk.model.Circuit>
     **/
    List<Circuit> queryCirByPid(int pid);

    /**
     * @Author 杨明瑞
     * @Description //TODO 查询小区
     * @Date 19:52 2019/3/21
     * @Param [housecircuitid]
     * @return java.util.List<com.jk.model.House>
     **/
    List<House> queryHouse(Integer housecircuitid);

    HashMap<String, Object> queryMyBro(int page, int rows, Integer userid);

    List<Store> queryStore(Integer cityid);

    List<Broker> queryBroInfo(Integer id);
}
