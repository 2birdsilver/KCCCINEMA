package com.example.kcccinema.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookingController {
	
	@RequestMapping("/book")
	public String book1() {
		return "book/booking1";
	}
	
	@RequestMapping("/book2")
	public String book2() {
		return "book/booking2";
	}
	
	@RequestMapping("/book3")
	public String book3() {
		return "book/booking3";
	}

} 
