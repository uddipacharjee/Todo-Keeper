package com.tutorial.todo.rest;

import com.tutorial.model.Todo;
import com.tutorial.todo.service.TodoService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TodoRestController {
    @Autowired
    private TodoService2 service;

    @RequestMapping(value="/todo/",method= RequestMethod.GET)
    public List<Todo> listAllTodos(){
        List<Todo> users=service.retrieveTodos("uddip");
        return users;
    }

    @RequestMapping(value = "/todo/{id}", method = RequestMethod.GET)
    public Todo retrieveTodo(@PathVariable("id") int id){
        return service.retrieveTodo(id);
    }
}
