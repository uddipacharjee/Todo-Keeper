package com.tutorial.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SignUpController {
	@RequestMapping(value="/signup")
	public String sendSignup() {
		return "signup";
	}
}
