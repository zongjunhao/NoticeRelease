package com.zjh.notice.mapper;

import com.zjh.notice.model.Todo;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zongjunhao
 */
@Mapper
public interface TodoMapper {
    /**
     * 按条件查看待办
     *
     * @param userId     用户ID
     * @param isFinished 是否完成
     * @param level      待办级别
     * @return 待办事项列表
     */
    List<Todo> findTodosByCondition(String userId, int isFinished, int level);

    /**
     * 查看已过期待办
     *
     * @param userId 用户ID
     * @param level  待办级别
     * @return 待办事项列表
     */
    List<Todo> findExpiredTodos(String userId, int level);

    /**
     * 添加一条todo记录
     *
     * @param title        标题
     * @param content      内容
     * @param level        等级
     * @param endTimestamp 结束时间
     * @param userId       用户ID
     * @return 是否添加成功
     */
    @Insert("insert into todo(todo_title, todo_content, todo_level, todo_endtime, todo_user_id) values(#{title}, #{content}, #{level}, #{endTimestamp}, #{userId})")
    int addTodo(String title, String content, int level, Timestamp endTimestamp, String userId);

    /**
     * 完成一条Todo
     *
     * @param todoId TodoID
     * @return 是否更新成功
     */
    @Update("update todo set todo_is_finished = 1 , todo_finishtime = now() where todo_id = #{todoId} ")
    int finishTodo(String todoId);

    /**
     * 修改一条todo记录
     *
     * @param title        标题
     * @param content      内容
     * @param level        等级
     * @param endTimestamp 结束时间
     * @param todoId       todoID
     * @return 是否修改成功
     */
    @Update("update todo set todo_title = #{title}, todo_content = #{content}, todo_level = #{level}, todo_endtime = #{endTimestamp} where todo_id = #{todoId}")
    int changeTodo(String title, String content, int level, Timestamp endTimestamp, String todoId);
}
