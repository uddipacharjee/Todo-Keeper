package com.tutorial.services;

import com.tutorial.model.Todo;

import java.util.Date;
import java.util.List;

public interface TodoService {
    //public void addTodo(Todo todo);
    public void updateTodo(Todo todo);
    public void deleteTodo(int id);
    public List<Todo> retrieveTodos(String user);
    public Todo retrieveTodo(int id);

    void addTodo(String loggedInUserName, String desc, Date targetDate, boolean b);

    void changeTodoStatus(int id);
}
