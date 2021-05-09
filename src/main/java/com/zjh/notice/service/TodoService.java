package com.zjh.notice.service;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.model.Todo;
import org.apache.ibatis.annotations.Select;

import java.sql.Timestamp;

/**
 * @author zongjunhao
 */
public interface TodoService {
    /**
     * 按条件查看待办
     *
     * @param userId     用户ID
     * @param isFinished 是否完成
     * @param level      待办级别
     * @return 待办事项列表
     */
    ResponseData getTodos(String userId, int isFinished, int level);

    /**
     * 查询单条TODO
     * @param todoId TodoID
     * @return Todo信息
     */
    ResponseData getTodo(String todoId);

    /**
     * 添加todo
     *
     * @param title   标题
     * @param content 内容
     * @param level   等级
     * @param endTime 日期
     * @param userId  用户ID
     * @return 是否添加成功
     */
    ResponseData addTodo(String title, String content, int level, String endTime, String userId);

    /**
     * 完成一条TODO
     *
     * @param todoId todoID
     * @return 是否修改成功
     */
    ResponseData finishTodo(String todoId);

    /**
     * 修改一条todo记录
     *
     * @param title   标题
     * @param content 内容
     * @param level   等级
     * @param endTime 结束时间
     * @param todoId  todoID
     * @return 是否修改成功
     */
    ResponseData changeTodo(String title, String content, int level, String endTime, String todoId);
}
