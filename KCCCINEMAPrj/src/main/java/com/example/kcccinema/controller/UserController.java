package com.example.kcccinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {

	@RequestMapping("/login")
	public String login() {
		return "user/login";
	}

	@RequestMapping("/signup")
	public String signup() {
		return "user/signup";
	}
}
