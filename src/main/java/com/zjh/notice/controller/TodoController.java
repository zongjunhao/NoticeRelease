package com.zjh.notice.controller;

import com.zjh.notice.kit.ResponseData;
import com.zjh.notice.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zongjunhao
 */
@RestController
@RequestMapping("todo")
public class TodoController {
    private final static Logger logger = LoggerFactory.getLogger(IndexController.class);
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping("getTodos")
    public ResponseData getTodos(int isFinished, int level, String userId) {
        ResponseData response;
        response = todoService.getTodos(userId, isFinished, level);
        return response;
    }

    @RequestMapping("addTodo")
    public ResponseData addTodo(String title, String content, int level, String endTime, String userId) {
        ResponseData response;
        response = todoService.addTodo(title, content, level, endTime, userId);
        return response;
    }

    @RequestMapping("finishTodo")
    public ResponseData finishTodo(String todoId){
        ResponseData response;
        response = todoService.finishTodo(todoId);
        return response;
    }

    @RequestMapping("changeTodo")
    public ResponseData changeTodo(String title, String content, int level, String endTime, String todoId){
        ResponseData response;
        response = todoService.changeTodo(title, content, level, endTime, todoId);
        return response;
    }
}
