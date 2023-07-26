package com.example.kcccinema.controller.mypage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kcccinema.service.mypage.IMypageService;
import com.example.kcccinema.vo.mypage.MypageUserVO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {
	static final Logger logger = LoggerFactory.getLogger(MypageController.class);
	
	@Autowired
	IMypageService mypageService; 
	
	
	//마이페이지 호출 
	@RequestMapping(value="/mypage/login", method=RequestMethod.GET)
	public String login() {
		return "user/login";
	}
	
	@RequestMapping(value="/mypage/login", method=RequestMethod.POST)
	public String login(String userid, String password, HttpSession session, Model model) {
		MypageUserVO mypageuservo = mypageService.selectUser(userid);
		if(mypageuservo != null) {
			String dbPassword = mypageuservo.getPassword();
			if(dbPassword == null) {
				model.addAttribute("message","NOT_VALID_USER");
			}else {
				if(dbPassword.equals(password)) {
					session.setAttribute("userId", userid);
					session.setAttribute("userName", mypageuservo.getUserName());
					session.setAttribute("userAge", mypageuservo.getUserAge());
					session.setAttribute("userBirthDate", mypageuservo.getUserBirthDate());
					return "mypage/mypage";
					
				}else {
					model.addAttribute("message", "WRONG_PASSWORD");
				}
			}
		}else {
			model.addAttribute("message","USER_NOT_FOUND");
		}
		session.invalidate();
		return "user/login";
	}
	
	//로그아웃
	@RequestMapping(value="/mypage/logout", method=RequestMethod.GET)
	public String logout(HttpSession session, HttpServletRequest request) {
		session.invalidate();
		return "redirect: /";
	}

	//회원정보수정 GET
	@RequestMapping(value="/userupdate", method=RequestMethod.GET)
	public String userUpdate(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("userId");
		if(userId != null && !userId.equals("")) {
			MypageUserVO mypageuservo = mypageService.selectUser(userId);
			model.addAttribute("mypageuservo", mypageuservo);
			model.addAttribute("message","UPDATE_USER_INFO");
			return "mypage/mypage";
		}else {
			model.addAttribute("message","NOT_LOGIN_USER");
			return "user/login";
		}
			
	}
	//회원정보수정 POST
	@RequestMapping(value="/userupdate", method=RequestMethod.POST)
	public String userUpdate(MypageUserVO mypageuservo, HttpSession session, Model model) {
			try {
				mypageService.updateUser(mypageuservo);
				model.addAttribute("message","UPDATED_MEMBER_INFO");
				model.addAttribute("mypageuservo",mypageuservo);
				session.setAttribute("userId", mypageuservo.getUserId());
				session.setAttribute("userName", mypageuservo.getUserName());
				session.setAttribute("userAge", mypageuservo.getUserAge());
				session.setAttribute("userBirthDate", mypageuservo.getUserBirthDate());
				return "/mypage/mypage";
			}catch(Exception e) {
				model.addAttribute(e.getMessage());
				e.printStackTrace();
				return "user/login";
			}
	}
	
	//회원탈퇴 GET
	@RequestMapping(value="/userquit", method=RequestMethod.GET)
	public String userQuit(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("userId");
		if(userId != null && !userId.equals("")) {
			MypageUserVO mypageuservo = mypageService.selectUser("userId");
			model.addAttribute("mypageuservo",mypageuservo);
			model.addAttribute("message","MEMBER_PW_RE");
			return "mypage/userquit";
			
		}else {
			//userid가 세션에 없을 때 
			model.addAttribute("message","NOT_LOGIN_USER");
			return "user/login";
		}
	
	}
	
	//회원탈퇴 POST
	@RequestMapping(value="/userquit", method=RequestMethod.POST)
	public String userQuit(String password,HttpSession session, Model model) {
		try {
			MypageUserVO mypageuservo = new MypageUserVO();
			mypageuservo.setUserId((String)session.getAttribute("userId"));
			String dbpw = mypageService.getPassword(mypageuservo.getUserId());
			if(password != null && password.equals(dbpw)) {
				mypageuservo.setPassword(password);
				mypageService.deleteMember(mypageuservo);
				session.invalidate();
				return "user/login";
			}else {
				model.addAttribute("message","WRONG_PASSWORD");
				return "mypage/userquit";
			}
		}catch(Exception e) {
			model.addAttribute("message","DELETE_FAIL");
			e.printStackTrace();
			return "mypage/userquit";
		}
	
		
	}
	
}
