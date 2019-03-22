package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.TaskDao;
import com.jk.model.HeTong;
import com.jk.model.Task;
import com.jk.model.TaskBack;
import com.jk.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired
    private TaskDao taskDao;

    //新增任务
    @Override
    public void save(Task task) {
        taskDao.saveTask(task);
    }

    //根据合同id查询任务表
    @Override
    public Task queryRWById(Integer id) {
        return taskDao.queryRWById(id);
    }
    //根据合同id修改合同状态
    @Override
    public void updateHeTong(Integer id, Integer status) {
        taskDao.updateHeTong(id,status);
    }

    @Override
    public int random() {
        return taskDao.random();
    }

    @Override
    public HashMap<String, Object> queryTask(Integer page, Integer rows,Task task) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total=taskDao.queryTaskCount(task);
        int start=(page-1)*rows;
        List<Task> list=taskDao.queryTaskList(start,rows,task);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public HashMap<String, Object> queryMyTask(Integer page, Integer rows, Integer userid, Task task) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total=taskDao.queryMyTaskCount(userid,task);
        int start=(page-1)*rows;
        List<Task> list=taskDao.queryMyTaskList(start,rows,task,userid);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public void addTaskBask(TaskBack taskBack) {
        Integer select = taskBack.getSelect();
        Integer taskid = taskBack.getTaskid();
        Integer hid=taskDao.queryTaskByIdIsHeTongId(taskid);
        if(select==1){
            taskDao.updateHeTongDate(hid,Integer.parseInt(taskBack.getDeadline()));

        }else{
            taskDao.updateHeTong(1,hid);
        }
        taskDao.updateTask(taskid);
        taskDao.addTaskBack(taskBack);
    }

    @Override
    public HashMap<String, Object> queryTaskBack(Integer page, Integer rows, TaskBack taskBack) {
        HashMap<String, Object> hashMap = new HashMap<>();
        long total=taskDao.queryTaskBackCount(taskBack);
        int start=(page-1)*rows;
        List<TaskBack> list=taskDao.queryTaskBackList(start,rows,taskBack);
        hashMap.put("total",total);
        hashMap.put("rows",list);
        return hashMap;
    }

    @Override
    public void delTaskBack(Integer id) {
        taskDao.delTaskBack(id);
    }


}
