package com.jk.dao;

import com.jk.model.HeTong;
import com.jk.model.Task;
import com.jk.model.TaskBack;
import org.apache.ibatis.annotations.*;

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
    List<Task> queryTaskList(@Param("start") int start,@Param("rows")Integer rows,@Param("task")Task task);
    @Select("<script>"
            + "select count(*) from z_task t,t_house h,t_user u  where t.htid=h.houseid  and u.id=t.supportid and u.roleid=2 and t.status=0"
            + " and  1=1 and u.id=#{userid}"
            + "<if test='task.phone!=null'>"
            + "and t.phone = '${task.phone}'"
            + "</if>"
            + "</script>")
    long queryMyTaskCount(@Param("userid") Integer userid,@Param("task") Task task);
    @Select("<script>"
            + "selec" +
            "t t.id,t.content,t.email,t.supportid,t.phone,h.housename hname from z_task t,t_house h,t_user u  where t.htid=h.houseid and u.id=t.supportid and u.roleid=2 and t.status=0"
            + " and  1=1 and u.id= #{userid}"
            + "<if test='task.phone!=null'>"
            + "and t.phone = '${task.phone}'"
            + "</if>"
            +"LIMIT #{start},#{rows}"
            + "</script>")
    List<Task> queryMyTaskList(@Param("start") int start,@Param("rows")Integer rows,@Param("task")Task task,@Param("userid")Integer userid);
    @Select("select t.htid  from z_task t where t.id= #{taskid}")
    Integer queryTaskByIdIsHeTongId(Integer taskid);
    @Update("update z_hetong z  set latedate=DATE_ADD(z.latedate,INTERVAL '${parseInt}' YEAR),z.deadline=#{parseInt},z.status=2 where z.id =#{hid}")
    void updateHeTongDate(@Param("hid") Integer hid,@Param("parseInt") int parseInt);
    @Insert("insert into z_taskback(taskid,supportid,feedback,backtime) values(#{taskid},#{supportid},#{feedback},SYSDATE())")
    void addTaskBack(TaskBack taskBack);
    @Update("update z_task set status=2 where id=#{taskid}")
    void updateTask(Integer taskid);
    @Select("<script>"
            + "select count(*) from z_taskback t"
            + " where    1=1 "
            + "<if test='taskBack.taskid!=null'>"
            + "and t.taskid = '${taskBack.taskid}'"
            + "</if>"
            + "</script>")
    long queryTaskBackCount(@Param("taskBack") TaskBack taskBack);

    @Select("<script>"
            + "select * from z_taskback t"
            + " where   1=1 "
            + "<if test='taskBack.taskid!=null'>"
            + "and t.taskid = '${taskBack.taskid}'"
            + "</if>"
            + "</script>")
    List<TaskBack> queryTaskBackList(@Param("start") int start, @Param("rows") Integer rows, @Param("taskBack") TaskBack taskBack);

    @Delete("delete from  z_taskback where id=#{id}")
    void delTaskBack(Integer id);
}
