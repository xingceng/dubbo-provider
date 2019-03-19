package com.jk.dao;

import com.jk.model.HeTong;
import com.jk.model.Task;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

public interface TaskDao {

    //新增任务表
    @Insert("insert into z_task(htid,content,phone,brokerid,email) values(#{htid},#{content},#{phone},#{brokerid},#{email})")
    void saveTask(Task task);
    //根据合同id查询任务表
    @Select("select * from z_task where htid=#{id}")
    Task queryRWById(Integer id);
    //根据合同id修改合同表
    @Update("update z_hetong set status=#{status} where id=#{id}")
    void updateHeTong(@Param("id") Integer id,@Param("status") Integer status);
    //随机从经纪人表取出一个id
    @Select("SELECT id FROM t_broker  ORDER BY  RAND() LIMIT 1")
    int random();
    @Select("<script>"
            + "select count(*) from z_task t,t_house h where t.htid=h.houseid "
            + " and  1=1"
            + "<if test='task.phone!=null'>"
            + "and t.phone = '${task.phone}'"
            + "</if>"
            + "</script>")
    long queryTaskCount(@Param("task")Task task);
    @Select("<script>"
            + "select t.id,t.content,t.email,t.supportid,t.phone,h.housename hname from z_task t,t_house h where t.htid=h.houseid "
            + " and  1=1"
            + "<if test='task.phone!=null'>"
            + "and t.phone = '${task.phone}'"
            + "</if>"
            +"LIMIT #{start},#{rows}"
            + "</script>")
    List<Task> queryHeTong(@Param("start") int start,@Param("rows")Integer rows,@Param("task")Task task);
}
