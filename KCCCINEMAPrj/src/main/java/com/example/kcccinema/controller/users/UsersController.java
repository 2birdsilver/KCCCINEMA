package com.example.kcccinema.controller.users;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kcccinema.model.UsersVO;
import com.example.kcccinema.service.users.IUsersService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private IUsersService usersService;

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "user/login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(String userId, String userPassword, HttpSession session, Model model) {
		UsersVO user = usersService.selectMember(userId);
		if (user != null) {
			String dbPassword = user.getUserPassword();
			if (dbPassword == null) {
				model.addAttribute("message", "NOT_VALID_USER");
			} else {
				if (dbPassword.equals(userPassword)) {
					session.setAttribute("userId", userId);
					session.setAttribute("userName", user.getUserName());
					session.setAttribute("userAge", user.getUserAge());
					return "redirect:/";
				} else {
					model.addAttribute("message", "WRONG_PASSWORD");
				}
			}
		} else {
			model.addAttribute("message", "USER_NOT_FOUND");
		}
		session.invalidate();
		return "user/login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect:/";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String insertMember() {
		return "user/signup";
	}

	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String insertMember(UsersVO user, HttpSession session, Model model) {
		try {
			if (!user.getUserPassword().equals(user.getUserPassword2())) {
				model.addAttribute("user", user);
				model.addAttribute("message", "MEMBER_PW_RE");
				return "user/signup";
			}
			usersService.insertMember(user);
		} catch (DuplicateKeyException e) {
			user.setUserId(null);
			model.addAttribute("user", user);
			model.addAttribute("message", "ID_ALREADY_EXIST");
			return "user/signup";
		}
		session.invalidate();
		return "redirect:/";
	}
}
