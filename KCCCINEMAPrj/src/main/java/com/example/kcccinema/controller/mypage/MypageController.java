package com.example.kcccinema.controller.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.kcccinema.service.mypage.IMypageService;
import com.example.kcccinema.vo.mypage.MypageUserVO;

import jakarta.servlet.http.HttpSession;

@Controller
public class MypageController {
	
	@Autowired
	IMypageService mypageService;
	
	@RequestMapping(value="/mypage", method=RequestMethod.GET)
	public String mypage(String userId, Model model) {
		MypageUserVO mypageUserVO = mypageService.selectUser("admin@admin.com");
		model.addAttribute("mypageUserVO", mypageUserVO);
		return "mypage/mypage";
	}
	//내가 예매한 영화확인 GET
	@RequestMapping("/checkReservation")
	public String checkReservation() {
		return "mypage/checkReservation"; 
	} 
	//회원정보수정 GET
	@RequestMapping(value="/userupdate", method=RequestMethod.GET)
	public String userUpdate(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("userId");
		model.addAttribute(userId);
		return "mypage/userupdate";
			
	}
	//회원정보수정 POST
	@RequestMapping(value="/userupdate", method=RequestMethod.POST)
	public String userUpdate(MypageUserVO mypageuservo, HttpSession session, Model model) {
			mypageService.updateUser(mypageuservo);
			model.addAttribute(mypageuservo);
			return "mypage/mypage";
	}
	
	//회원탈퇴 GET
	@RequestMapping(value="/userquit", method=RequestMethod.GET)
	public String userQuit(HttpSession session, Model model) {
		String userId = (String)session.getAttribute("userId");
		MypageUserVO mypageuservo = mypageService.selectUser(userId);
		model.addAttribute(mypageuservo);
		return "mypage/userquit";
	}
	
	//회원탈퇴 POST
	@RequestMapping(value="/userquit", method=RequestMethod.POST)
	public String userQuit(String password,HttpSession session, Model model) {
		MypageUserVO mypageuservo = new MypageUserVO();
		mypageuservo.setUserId((String)session.getAttribute("userId"));
		mypageService.deleteMember(mypageuservo);
		session.invalidate();
		return "mypage/mypage";
		
	}
	
}
