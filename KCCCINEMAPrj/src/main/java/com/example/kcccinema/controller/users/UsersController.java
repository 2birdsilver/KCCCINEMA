package com.example.kcccinema.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kcccinema.model.UsersVO;
import com.example.kcccinema.service.users.IUsersService;

@Controller
public class UsersController {
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private IUsersService usersService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signup() {
		return "user/signup";
	}

	// 회원가입
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signup(UsersVO user) throws Exception {

		logger.info("signUp 진입");

		// 회원가입 서비스 실행
		usersService.insertUsers(user);

		logger.info("signUp Service 성공");

		return "redirect:/";

	}
}
