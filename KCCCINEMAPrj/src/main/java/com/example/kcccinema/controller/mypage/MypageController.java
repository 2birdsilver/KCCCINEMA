package com.example.kcccinema.controller.mypage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MypageController {
	
	@RequestMapping("/mypage")
	public String mypage() {
		return "mypage/mypage";
	}
	
	@RequestMapping("/checkReservation")
	public String checkReservation() {
		return "mypage/checkReservation";
	}
	
	@RequestMapping("/userupdate")
	public String userUpdate() {
		return "mypage/userupdate";
	}
	
	@RequestMapping("/userquit")
	public String userQuit() {
		return "mypage/userquit";
	}

}
