package com.tutorial.todo;

import com.tutorial.model.Todo;
import com.tutorial.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Date;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    private TodoService service;
    @RequestMapping(value="/list-todos", method= {RequestMethod.GET})
    public String showTodoList(ModelMap model){
        String user= (String) model.get("name");
        model.addAttribute("todos",service.retrieveTodos(user));
        if(user==null) {
            model.put("errorMessage","You have to first Login");
            return "login";
        }
        return "listOfTodo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showTodoPage() {
        return "todo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(@RequestParam String desc,ModelMap model){
        service.addTodo((String)model.get("name"),desc,new Date(),false);
        //model.addAttribute("todos",service.retrieveTodos((String)model.get("name")));
        model.clear();

        return "redirect:/list-todos";
        //return "forward:/list-todos";
    }
}
