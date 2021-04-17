package com.zjh.notice.model;


import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author zongjunhao
 */
@SuppressWarnings("unused")
public class Todo {

  private long todoId;
  private String todoTitle;
  private String todoContent;
  private long todoLevel;
  private long todoIsFinished;
  private long todoUserId;
  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private java.sql.Timestamp todoEndtime;
  private java.sql.Timestamp todoFinishtime;
  private java.sql.Timestamp todoAddtime;
  private java.sql.Timestamp todoUpdatetime;


  public long getTodoId() {
    return todoId;
  }

  public void setTodoId(long todoId) {
    this.todoId = todoId;
  }


  public String getTodoTitle() {
    return todoTitle;
  }

  public void setTodoTitle(String todoTitle) {
    this.todoTitle = todoTitle;
  }


  public String getTodoContent() {
    return todoContent;
  }

  public void setTodoContent(String todoContent) {
    this.todoContent = todoContent;
  }


  public long getTodoLevel() {
    return todoLevel;
  }

  public void setTodoLevel(long todoLevel) {
    this.todoLevel = todoLevel;
  }


  public long getTodoIsFinished() {
    return todoIsFinished;
  }

  public void setTodoIsFinished(long todoIsFinished) {
    this.todoIsFinished = todoIsFinished;
  }


  public long getTodoUserId() {
    return todoUserId;
  }

  public void setTodoUserId(long todoUserId) {
    this.todoUserId = todoUserId;
  }


  public java.sql.Timestamp getTodoEndtime() {
    return todoEndtime;
  }

  public void setTodoEndtime(java.sql.Timestamp todoEndtime) {
    this.todoEndtime = todoEndtime;
  }


  public java.sql.Timestamp getTodoFinishtime() {
    return todoFinishtime;
  }

  public void setTodoFinishtime(java.sql.Timestamp todoFinishtime) {
    this.todoFinishtime = todoFinishtime;
  }


  public java.sql.Timestamp getTodoAddtime() {
    return todoAddtime;
  }

  public void setTodoAddtime(java.sql.Timestamp todoAddtime) {
    this.todoAddtime = todoAddtime;
  }


  public java.sql.Timestamp getTodoUpdatetime() {
    return todoUpdatetime;
  }

  public void setTodoUpdatetime(java.sql.Timestamp todoUpdatetime) {
    this.todoUpdatetime = todoUpdatetime;
  }


  @Override
  public String toString() {
    return "Todo{" +
            "todoId=" + todoId +
            ", todoTitle='" + todoTitle + '\'' +
            ", todoContent='" + todoContent + '\'' +
            ", todoLevel=" + todoLevel +
            ", todoIsFinished=" + todoIsFinished +
            ", todoUserId=" + todoUserId +
            ", todoEndtime=" + todoEndtime +
            ", todoFinishtime=" + todoFinishtime +
            ", todoAddtime=" + todoAddtime +
            ", todoUpdatetime=" + todoUpdatetime +
            '}';
  }
}
