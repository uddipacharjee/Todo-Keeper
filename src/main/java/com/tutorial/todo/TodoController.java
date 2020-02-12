package com.tutorial.todo;

import com.tutorial.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@SessionAttributes("name")
public class TodoController {
    @Autowired
    private TodoService service;
    @RequestMapping(value="/list-todos", method= RequestMethod.GET)
    public String showLoginPage(ModelMap model, String name){
        String user= (String) model.get("name");
        System.out.println(user);
        model.addAttribute("todos",service.retrieveTodos(user));
        if(user==null) {
            model.put("errorMessage","You have to first Login");
            return "login";
        }
        return "list-todos";
    }
}
