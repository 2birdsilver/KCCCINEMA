package com.example.kcccinema.controller.Book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.service.book.IBooking2Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Booking2Controller {
		
	@Autowired
	private IBooking2Service booking2Service;
	
	@RequestMapping(value="/booking2", method=RequestMethod.GET)
	public String book2(HttpSession session, Model model) throws Exception {
		//전 페이지에서 선택한 날짜, 영화, 영화관 지점, 시작시간, 종료시간, 상영관을 가져옴.
		//MovieVO movieVO = booking2Service.getMovie(movieTitleFinal);
		//model.addAttribute("date", dateFinal);
		//model.addAttribute("movieVO", movieVO);
	//	model.addAttribute("cinema", cinemaFinal);
	//	model.addAttribute("theater", theaterFinal);
	//	model.addAttribute("startTime", startTimeFinal);
	//	model.addAttribute("endTime", endTimeFinal);
		return "book/booking2";
	}
	
	@RequestMapping(value = "/booking2ok")
	public String booking2ok(HttpServletRequest request, Model model) throws Exception {
		
		String tagName = request.getParameter("tagName_value");
		String hangle = request.getParameter("hangle_value");
		String movieNm = request.getParameter("movieNm_value");
		String movieImg = request.getParameter("movieImg_value");
		String playKindNm = request.getParameter("playKindNm_value");
		String brchNm = request.getParameter("brchNm_value");
		String theater = request.getParameter("theater_value");
		String playDe = request.getParameter("playDe_value");
		String dowNm = request.getParameter("dowNm_value");
		String playTime = request.getParameter("playTime_value");
		
		String adultCnt = request.getParameter("adultCount_value");
		String youngCnt = request.getParameter("youngCount_value");
		
		String adultPrice = request.getParameter("adultPrice_value");
		String youngPrice = request.getParameter("youngPrice_value");
		String totalPrice = request.getParameter("totalPrice_value");
		
		String seat1 = request.getParameter("seat1_value");
		String seat2 = request.getParameter("seat2_value");
		String seat3 = request.getParameter("seat3_value");
		String seat4 = request.getParameter("seat4_value");
		String seat5 = request.getParameter("seat5_value");
		String seat6 = request.getParameter("seat6_value");
		String seat7 = request.getParameter("seat7_value");
		String seat8 = request.getParameter("seat8_value");
	
		model.addAttribute("tagName",tagName);
		model.addAttribute("hangle",hangle);
		model.addAttribute("movieNm",movieNm);
		model.addAttribute("movieImg",movieImg);
		model.addAttribute("playKindNm",playKindNm);
		model.addAttribute("playDe",playDe);
		model.addAttribute("brchNm",brchNm);
		model.addAttribute("theater",theater);
		model.addAttribute("dowNm",dowNm);
		model.addAttribute("playTime",playTime);
		
		model.addAttribute("adultCnt",adultCnt);
		model.addAttribute("youngCnt",youngCnt);
		
		model.addAttribute("adultPrice",adultPrice);
		model.addAttribute("youngPrice",youngPrice);	
		model.addAttribute("totalPrice",totalPrice);
		
		model.addAttribute("seat1",seat1);
		model.addAttribute("seat2",seat2);
		model.addAttribute("seat3",seat3);
		model.addAttribute("seat4",seat4);
		model.addAttribute("seat5",seat5);
		model.addAttribute("seat6",seat6);
		model.addAttribute("seat7",seat7);
		model.addAttribute("seat8",seat8);
		
		return "booking/booking3";
	}
}
