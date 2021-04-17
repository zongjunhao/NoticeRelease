package com.zjh.notice.service.impl;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.kit.ResultCodeEnum;
import com.zjh.notice.kit.Utils;
import com.zjh.notice.mapper.TodoMapper;
import com.zjh.notice.model.Todo;
import com.zjh.notice.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author zongjunhao
 */
@Service
public class TodoServiceImpl implements TodoService {
    private final static Logger logger = LoggerFactory.getLogger(TodoServiceImpl.class);
    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoMapper todoMapper) {
        this.todoMapper = todoMapper;
    }

    @Override
    public ResponseData getTodos(String userId, int isFinished, int level) {
        ResponseData response = new ResponseData();
        try {
            List<Todo> todos = todoMapper.findTodosByCondition(userId, isFinished, level);
            if (todos.isEmpty()) {
                response.setResult(ResultCodeEnum.DB_FIND_FAILURE);
            } else {
                response.setData(todos);
                response.setResult(ResultCodeEnum.DB_FIND_SUCCESS);
            }
        } catch (Exception e) {
            logger.error("get todo error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData addTodo(String title, String content, int level, String endTime, String userId) {
        ResponseData response = new ResponseData();
        try {
            Timestamp endTimestamp = Utils.strToTimestamp(endTime);
            int result = todoMapper.addTodo(title, content, level, endTimestamp, userId);
            if (result == 1) {
                response.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
            }
        } catch (Exception e) {
            logger.error("add todo error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData finishTodo(String todoId) {
        ResponseData response = new ResponseData();
        try {
            Timestamp date = new Timestamp(System.currentTimeMillis());
            int result = todoMapper.finishTodo(todoId);
            if (result == 1) {
                response.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
            }
        } catch (Exception e) {
            logger.error("finish todo error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }

    @Override
    public ResponseData changeTodo(String title, String content, int level, String endTime, String todoId) {
        ResponseData response = new ResponseData();
        try {
            Timestamp endTimestamp = Utils.strToTimestamp(endTime);
            int result = todoMapper.changeTodo(title, content, level, endTimestamp, todoId);
            if (result == 1) {
                response.setResult(ResultCodeEnum.DB_UPDATE_SUCCESS);
            } else {
                response.setResult(ResultCodeEnum.DB_UPDATE_ERROR);
            }
        } catch (Exception e) {
            logger.error("change todo error", e);
            response.setResult(ResultCodeEnum.SERVER_ERROR);
        }
        return response;
    }
}
