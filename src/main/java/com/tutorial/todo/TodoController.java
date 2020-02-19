package com.tutorial.todo;

import com.tutorial.model.Todo;
import com.tutorial.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    private TodoService service;

    @RequestMapping(value="/list-todos", method= {RequestMethod.GET})
    public String showTodoList(ModelMap model){
        String user= (String) model.get("name");
        System.out.println(user);
        System.out.println(service.retrieveTodos(user));
        model.addAttribute("todos",service.retrieveTodos(user));
        if(user==null) {
            model.put("errorMessage","You have to first Login");
            return "login";
        }
        return "listOfTodo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo",new Todo());
        return "todo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(@Valid Todo todo, BindingResult result, ModelMap model){
        System.out.println(todo.toString());
        if(result.hasErrors())
            return "todo";

        service.addTodo((String)model.get("name"),todo.getDesc(),new Date(),false);
        //model.addAttribute("todos",service.retrieveTodos((String)model.get("name")));
        model.clear();

        return "redirect:/list-todos";
        //return "forward:/list-todos";
    }
    @RequestMapping(value="/delete-todo", method=RequestMethod.GET)
    public String deleteTodo(@RequestParam int id,ModelMap model){
        service.deleteTodo(id);
        model.clear();
        return "redirect:/list-todos";
    }
    @RequestMapping(value="/update-todo",method=RequestMethod.GET)
    public String showUpdateTodoPage(ModelMap model,@RequestParam int id){
        Todo todo=service.retrieveTodo(id);
        System.out.println("show todo page"+todo);
        model.addAttribute("todo",todo);
        System.out.println("update : "+model.get("name"));
        return "todo";
    }
    @RequestMapping(value="/update-todo",method = RequestMethod.POST)
    public String updateTodo(@Valid @ModelAttribute("todo") Todo todo,BindingResult result,ModelMap model){
        if(result.hasErrors()){
            return "todo";
        }
        todo.setTargetDate(new Date());
        service.updateTodo(todo);

        return "redirect:/list-todos";
    }

}
