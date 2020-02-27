package com.tutorial.todo.service;

import com.tutorial.model.Todo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.logging.Logger;

@Service
public class TodoService2 {

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
        System.out.println("add\n"+todos);
    }

    public void updateTodo(Todo todo){
        todos.remove(todo);
        todos.add(todo);
        sort();
        System.out.println("update\n"+todos);
    }
    public void deleteTodo(int id){
        System.out.println("delete");
        Iterator<Todo> iterator=todos.iterator();
        while(iterator.hasNext()){
            Todo todo=iterator.next();
            if(todo.getId()==id){
                iterator.remove();

            }
        }
        sort();
        System.out.println("delete\n"+todos);
    }
    public void changeTodoStatus(int id){
        Iterator<Todo> iterator=todos.iterator();
        Todo expectedTodo=null;
        while(iterator.hasNext()){
            Todo todo=iterator.next();
            if(todo.getId()==id){
                expectedTodo=todo;
                break;
            }
        }
        if(expectedTodo.isDone())expectedTodo.setDone(false);
        else expectedTodo.setDone(true);
        updateTodo(expectedTodo);
        sort();
        System.out.println("change\n"+todos);
    }
    private void sort(){
        Collections.sort(todos, new Comparator<Todo>() {
            @Override
            public int compare(Todo t1, Todo t2) {
                return t1.getId()-t2.getId();
            }
        });
    }
}
