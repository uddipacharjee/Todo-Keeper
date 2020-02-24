package com.tutorial.welcome;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
//@SessionAttributes("name")
public class WelcomeController {
//	@Autowired
//	private LoginService service;

	@RequestMapping(value="/",method = RequestMethod.GET)
	public String showWelcomePage(ModelMap model){
		model.put("name",getLoggedInUserName());
		return "welcome";
	}
	private String getLoggedInUserName() {
		Object principal = SecurityContextHolder.getContext()
				.getAuthentication().getPrincipal();

		if (principal instanceof UserDetails)
			return ((UserDetails) principal).getUsername();

		return principal.toString();
	}
	/*

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

	*/
}
