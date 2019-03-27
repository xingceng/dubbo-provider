package com.jk.service.impl;

import com.jk.dao.UserDao;
import com.jk.model.PowerMenu;
import com.jk.model.Role;
import com.jk.model.Tree;
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

    @Override
    public List<Role> queryRoleList() {
        return userDao.queryRoleList();
    }

    @Override
    public List<Tree> queryPowerTree(Integer roleid) {
        //根据角色id查询对应的权限
        List<Tree> powerList = userDao.queryPowerByRoleId(roleid);
        List<Tree> list = userDao.queryNavTree();
        for (Tree navBean : list) {
            for (Tree power : powerList) {
                if(navBean.getId()==power.getId()){
                    navBean.setChecked(true);
                }
            }
        }
        return list;
    }

    @Override
    public void saveRolePower(Integer roleId, Integer[] powerIds) {
        //删除
        userDao.deleteRolePowerByRoleId(roleId);
        //新增
        for (int i =0;i<powerIds.length;i++){
            userDao.saveRolePower(roleId,powerIds[i]);
        }
    }

    @Override
    public List<PowerMenu> queryPowerMenuList(Integer powerId) {
        return userDao.queryPowerMenuList(powerId);
    }

    @Override
    public void savePowerMenu(PowerMenu menu) {
        userDao.savePowerMenu(menu);
    }

    @Override
    public void guanZhu(Integer houseid, Integer userid) {
        userDao.guanZhu(houseid,userid);
    }
}
