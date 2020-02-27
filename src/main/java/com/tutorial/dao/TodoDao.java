package com.tutorial.dao;

import com.tutorial.model.Todo;

import java.util.List;

public interface TodoDao {
    public void addTodo(Todo todo);
    public void updateTodo(Todo todo);
    public boolean deleteTodo(int id);
    public List<Todo> retrieveTodos(String user);
    public Todo retrieveTodo(int id);
    public void changeTodoStatus(int id);
}
