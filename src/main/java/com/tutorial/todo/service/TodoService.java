package com.tutorial.todo.service;

import com.tutorial.model.Todo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TodoService {

    private final static Logger LOGGER =
            Logger.getLogger("TodoService");


    private static List<Todo> todos=new ArrayList<>();

    private static int todoCount=3;
    static{
        todos.add(new Todo(1, "uddip", "Learn Spring MVC", new Date(),
                false));
        todos.add(new Todo(2, "uddip", "Learn Struts", new Date(), false));
        todos.add(new Todo(3, "uddip", "Learn Hibernate", new Date(),
                false));
    }

    public List<Todo> retrieveTodos(String user){
        List<Todo> filteredTodos=new ArrayList<Todo>();
        for (Todo todo :todos) {
            if(todo!=null && todo.getUser().equals(user))
                filteredTodos.add(todo);
        }
        //System.out.println(todo);
        return filteredTodos;
    }
    public Todo retrieveTodo(int id){
        for (Todo todo:todos) {
            if(todo.getId()==id){
                return todo;
            }
        }
        return null;
    }
    public void addTodo(String name,String desc,Date targetDate,boolean isDone){
        todos.add(new Todo(++todoCount,name,desc,targetDate,isDone));
    }

    public void updateTodo(Todo todo){
        todos.remove(todo);
        todos.add(todo);
    }
    public void deleteTodo(int id){
        Iterator<Todo> iterator=todos.iterator();
        while(iterator.hasNext()){
            Todo todo=iterator.next();
            if(todo.getId()==id){
                iterator.remove();
            }
        }
    }
}
