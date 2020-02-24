package com.tutorial.todo;

import com.tutorial.model.Todo;
import com.tutorial.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
//@SessionAttributes("name")
public class TodoController {
    @Autowired
    private TodoService service;

    @InitBinder
    protected void initBinder(WebDataBinder binder){
        SimpleDateFormat dateFormat=new SimpleDateFormat("dd/MM/yyyy");
        StringTrimmerEditor stringTrimmerEditor=new StringTrimmerEditor(true);

        binder.registerCustomEditor(Date.class,new CustomDateEditor(dateFormat,false));
        binder.registerCustomEditor(String.class,stringTrimmerEditor);
        System.out.println("strintrimmer");
    }

    private String getLoggedInUserName(){
        Object principal = SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();

        if (principal instanceof UserDetails)
            return ((UserDetails) principal).getUsername();

        return principal.toString();
    }

    @RequestMapping(value="/list-todos", method= {RequestMethod.GET})
    public String showTodoList(ModelMap model){
        String user= getLoggedInUserName();
        System.out.println(user);
        //System.out.println(service.retrieveTodos(user));
        model.addAttribute("todos",service.retrieveTodos(user));
//        if(user==null) {
//            model.put("errorMessage","You have to first Login");
//            return "login";
//        }
        return "listOfTodo";
    }

    @RequestMapping(value = "/add-todo", method = RequestMethod.GET)
    public String showAddTodoPage(ModelMap model) {
        model.addAttribute("todo",new Todo());
        return "todo";
    }

    @RequestMapping(value="/add-todo", method = RequestMethod.POST)
    public String addTodo(@Valid @ModelAttribute("todo") Todo todo, BindingResult result, ModelMap model){
        System.out.println(todo.toString());
        if(result.hasErrors())
            return "todo";

        service.addTodo(getLoggedInUserName(),todo.getDesc(),todo.getTargetDate(),false);
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
        //System.out.println("show todo page"+todo);
        model.addAttribute("todo",todo);
        //System.out.println("update : "+model.get("name"));
        return "todo";
    }
    @RequestMapping(value="/update-todo",method = RequestMethod.POST)
    public String updateTodo(@Valid @ModelAttribute("todo") Todo todo,BindingResult result,ModelMap model){
        if(result.hasErrors()){
            return "todo";
        }
        //todo.setTargetDate(new Date());
        service.updateTodo(todo);
        model.clear();
        return "redirect:/list-todos";
    }

}
