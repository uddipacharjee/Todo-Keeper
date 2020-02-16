package com.tutorial.login;

import com.tutorial.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class LoginController {
	@Autowired
	LoginService service;
	
	@RequestMapping(value="/login", method= RequestMethod.GET)
	public String showLoginPage(ModelMap model) {
		model.put("errorMessage","");
		return "login";
	}

	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String handleLoginRequest(@RequestParam String name,@RequestParam String password, ModelMap model) {

		if(!service.validateUser(name,password)){
			model.addAttribute("errorMessage","user is not valid");

			return "login";
		}
		//model.addAttribute("name", name);
		//model.addAttribute("password",password);

		model.put("name",name);

		return "welcome";
	}


}
