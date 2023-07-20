package com.example.kcccinema.controller.Book;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.service.book.IBooking3Service;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class Booking3Controller {
	
	@Resource
	private IBooking3Service booking3Service;

	@RequestMapping(value = "/booking3")
	public String booking3() throws Exception {
		return "book/booking3";
	}

	@RequestMapping(value = "/booking3ok")
	public String booking3ok(HttpServletRequest request, Model model) throws Exception {
		//ticket_id의 최대값 가져오기
		int maxNum = booking3Service.maxNum();
		String usePayment = request.getParameter("usePayment");
		if (usePayment == null) {
			usePayment = "off";
		}
		String movieTitle = request.getParameter("movieTitle");
		String moviePoster = request.getParameter("moviePoster");
		String cinema = request.getParameter("cinema");
		String theater = request.getParameter("theater");
		String runningTime = request.getParameter("runningTime");
		
		String adultCnt = request.getParameter("adultCnt");
		String youngCnt = request.getParameter("youngCnt");
		
		String adultPrice = request.getParameter("adultPrice");
		String youngPrice = request.getParameter("youngPrice");
		String totalPrice = request.getParameter("totalPrice");
		
		String[] seat = new String[8];
		for(int i=1;i<=8;i++) {
			seat[i-1] = request.getParameter("seat" + i + "_value");
		}

		model.addAttribute("maxNum", maxNum+1);
		model.addAttribute("usePayment", usePayment);
		model.addAttribute("movieTitle", movieTitle);
		model.addAttribute("moviePoster", moviePoster);
		model.addAttribute("cinema",cinema);
		model.addAttribute("theater",theater);
		model.addAttribute("runningTime",runningTime);
		model.addAttribute("adultCnt",adultCnt);
		model.addAttribute("adultPrice",adultPrice);
		model.addAttribute("youngPrice",youngPrice);
		model.addAttribute("totalPrice",totalPrice);

		model.addAttribute("seat0",seat[0]);
		model.addAttribute("seat1",seat[1]);
		model.addAttribute("seat2",seat[2]);
		model.addAttribute("seat3",seat[3]);
		model.addAttribute("seat4",seat[4]);
		model.addAttribute("seat5",seat[5]);
		model.addAttribute("seat6",seat[6]);
		model.addAttribute("seat7",seat[7]);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMdd");
		String today = formatter.format(new Date());
		String ticketNum = today + "-" + Integer.toString(maxNum);
		
//		dto.setTicketNum(ticketNum);
		
		booking3Service.insertTicket();
		
		String person = "";
		if(!adultCnt.equals(""))
			person += "성인 " + adultCnt + "명 ";
		if(!youngCnt.equals(""))	
			person += "청소년 " + youngCnt + "명 ";	
		
		String StrSeat = "";
		for(int i=1;i<=8;i++) {
			if(!(seat[i-1].equals(""))) {
				if(i==1) {
					StrSeat += seat[i-1];
					continue;
				}
				StrSeat += " ";
				StrSeat += seat[i-1];
			} else
				break;
		}
		return "book/bookingComplete";
	}
}
