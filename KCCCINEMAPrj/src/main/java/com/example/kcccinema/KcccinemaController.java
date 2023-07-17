package com.example.kcccinema;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class KcccinemaController {
	@RequestMapping("/")
	public String home() {
		return "mypage";
	}
}
