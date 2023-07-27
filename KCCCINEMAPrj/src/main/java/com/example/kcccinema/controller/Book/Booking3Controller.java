package com.example.kcccinema.controller.Book;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kcccinema.model.TicketVO;
import com.example.kcccinema.service.book.IBooking3Service;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Booking3Controller {
	
	@Resource
	private IBooking3Service booking3Service;

	@RequestMapping(value = "/booking3ok")
	public String booking3ok(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		int ticketId = Integer.valueOf(String.valueOf(session.getAttribute("ticketId")));
		System.out.println(ticketId);
		
		int totalNumber = Integer.valueOf(String.valueOf(session.getAttribute("totalNumber")));
		System.out.println(totalNumber);
		
		String userId = String.valueOf(session.getAttribute("userId"));
		System.out.println(userId);
		
		int scheduleId = Integer.valueOf(String.valueOf(session.getAttribute("scheduleId")));
		System.out.println(scheduleId);
		
		String startTime = String.valueOf(session.getAttribute("startTime"));
		System.out.println(startTime);
		
		String endTime = String.valueOf(session.getAttribute("endTime"));
		System.out.println(endTime);
		
		int totalPrice = Integer.valueOf(String.valueOf(session.getAttribute("totalPrice")));
		System.out.println(totalPrice);
		
		TicketVO ticketVO = new TicketVO();
		ticketVO.setTicketId(ticketId);
		ticketVO.setTotalNumber(totalNumber);
		ticketVO.setUserId(userId);
		ticketVO.setScheduleId(scheduleId);
		ticketVO.setStartTime(startTime);
		ticketVO.setEndTime(endTime);
		ticketVO.setTotalPrice(totalPrice);
		
		booking3Service.insertTicket(ticketVO);
		
		for(int i=1; i<totalNumber+1; i++) {
			//예매개수만큼 seat1~seattotalNumber까지 insert함
			String sessionKey ="seat"+ Integer.toString(i); //seat1
			String seat= String.valueOf(session.getAttribute(sessionKey));
			booking3Service.insertSeat(ticketId, scheduleId, seat);
		}
		
		return "book/bookingComplete";
	}
		
}
