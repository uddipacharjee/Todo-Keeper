package com.tutorial.login.service;

import org.springframework.stereotype.Service;

@Service
public class LoginService {
	public boolean validateUser(String user,String password) {
		return user.equalsIgnoreCase("uddip") && password.equalsIgnoreCase("123");

	}
}
