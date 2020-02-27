package com.tutorial.services;

import com.tutorial.dao.TodoDao;
import com.tutorial.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

public class TodoServiceImpl implements TodoService {
    @Autowired
    private TodoDao todoDao;


    @Override
    public void updateTodo(Todo todo) {
        todoDao.updateTodo(todo);
    }

    @Override
    public void deleteTodo(int id) {
        todoDao.deleteTodo(id);
    }

    @Override
    public List<Todo> retrieveTodos(String user) {
        return todoDao.retrieveTodos(user);
    }

    @Override
    public Todo retrieveTodo(int id) {

        return todoDao.retrieveTodo(id);
    }

    @Override
    public void addTodo(String loggedInUserName, String desc, Date targetDate, boolean b) {
        todoDao.addTodo(new Todo(0,loggedInUserName,desc,targetDate,b));
    }

    @Override
    public void changeTodoStatus(int id) {
        todoDao.changeTodoStatus(id);
    }
}
