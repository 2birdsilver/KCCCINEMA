package com.example.kcccinema.controller.mypage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kcccinema.service.mypage.CheckReserveService;
import com.example.kcccinema.service.mypage.ICheckReserveService;
import com.example.kcccinema.vo.mypage.CheckReserveVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class CheckReserveController {
	static final Logger logger = LoggerFactory.getLogger(CheckReserveController.class);
	
	@Autowired
	ICheckReserveService checkReserveService;
	
	@RequestMapping(value="/checkreserve", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/checkreserve", method=RequestMethod.POST)
	public String login(String userid, String password, HttpSession session, Model model) {
		CheckReserveVO checkreservevo = checkReserveService.selectReserve(userid);
		if(checkreservevo != null) {
			String dbPassword = checkreservevo.getPassword();
			if(dbPassword == null) {
				model.addAttribute("message","NOT_VALID_USER");
			}else {
				if(dbPassword.equals(password)) {
					session.setAttribute("userId", userid);
					session.setAttribute("userName",checkreservevo.getUserName());
					session.setAttribute("moviePoster",checkreservevo.getMoviePoster());
					session.setAttribute("movieTitle", checkreservevo.getMovieTitle());
					session.setAttribute("screenDate", checkreservevo.getScreenDate());
					session.setAttribute("theaterId", checkreservevo.getTheaterId());
					session.setAttribute("seatId", checkreservevo.getSeatId());
					return "mypage/checkReservation";
				}else {
					model.addAttribute("message","WRONG_PASSWORD");
				}
			}
		}else {
			model.addAttribute("message","USER_NOT_FOUND");
		}
		session.invalidate();
		return "user/login";
	}
	@RequestMapping(value="checkreserve/delete", method=RequestMethod.GET)
	public String deleteReserve(HttpSession session, Model model) {
		String userid = (String)session.getAttribute("userId");
		if(userid != null && !userid.equals("")) {
			CheckReserveVO checkreservevo = checkReserveService.selectReserve(userid);
			model.addAttribute("userId",userid);
			model.addAttribute("message", "MEMBER_PW_RE");
			return "mypage/checkReservation";
		}else {
			model.addAttribute("message","NOT_LOGIN_USER");
			return "user/login";
		}
	}
	@RequestMapping(value="checkreserve/delete", method=RequestMethod.POST)
	public String deleteReserve(String password, HttpSession session, Model model) {
		try {
			CheckReserveVO checkreservevo = new CheckReserveVO();
			checkreservevo.setUserId((String)session.getAttribute("userId"));
			String dbpw = checkReserveService.getPassword(checkreservevo.getUserId());
			if(password != null && password.equals(dbpw)) {
				checkreservevo.setPassword(password);
				checkReserveService.deleteReserve(checkreservevo);
				return "mypage/checkReservation";
			}else {
				model.addAttribute("message","WRONG_PASSWORD");
				return "mypage/checkReservation";
			}
		}catch(Exception e) {
			model.addAttribute("messsage", "DELETE_FAIL");
			e.printStackTrace();
			return "mypage/checkReservation";
		}
	}

}
