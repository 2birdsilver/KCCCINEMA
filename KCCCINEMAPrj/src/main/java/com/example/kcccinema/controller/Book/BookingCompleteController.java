package com.example.kcccinema.controller.Book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class BookingCompleteController {

	@RequestMapping("/bookingCompete")
	public String bookingComplete(HttpServletRequest request) throws Exception {
		return "book/bookingComplete";
	}
	
	@RequestMapping(value = "/bookingComplete_ok")
	public String bookingComplete_ok(HttpServletRequest request) throws Exception {
		return "book/bookingComplete";
	}
}
