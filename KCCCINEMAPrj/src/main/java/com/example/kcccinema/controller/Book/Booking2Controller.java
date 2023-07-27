package com.example.kcccinema.controller.Book;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;
import com.example.kcccinema.service.book.IBooking2Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Booking2Controller {
		
	@Autowired
	private IBooking2Service booking2Service;
	
	@RequestMapping(value="/booking2", method=RequestMethod.GET)
	public String book2(HttpSession session, Model model) throws Exception {
		String movieTitle = String.valueOf(session.getAttribute("movieTitle"));
		MovieVO movieVO = booking2Service.getMovie(movieTitle);
		
		//movieVO에 사진 변경해서 넣어서 보내야됨
		String base64Image = Base64.getEncoder().encodeToString(movieVO.getMoviePoster());
		movieVO.setBase64Image(base64Image);
		
		//전 페이지에서 선택한 날짜, 영화, 영화관 지점, 시작시간, 종료시간, 상영관을 가져와서 전달.
		model.addAttribute("date", session.getAttribute("date"));
		model.addAttribute("movieVO", movieVO);
		model.addAttribute("cinema", session.getAttribute("cinema"));
		model.addAttribute("theater", session.getAttribute("theater"));
		model.addAttribute("startTime", session.getAttribute("startTime"));
		model.addAttribute("endTime", session.getAttribute("endTime"));
		
		return "book/booking2";
	}
	
	@RequestMapping(value = "/booking2ok", method=RequestMethod.POST)
	public String booking2ok(HttpServletRequest request, Model model) throws Exception {
		HttpSession session = request.getSession();
		int totalPrice = Integer.valueOf(request.getParameter("totalPriceValue"));//총 금액
		int adultCount = 0;//어른 개수
		int youngCount = 0;//어린이 개수
		int totalNumber = 0; //총 개수
		
		//어른 개수와 어린이 개수를 이용해 총 개수 저장. null이면 0으로 초기화
		if(request.getParameter("adultCountValue").equals(""))
			adultCount=0;
		else
			adultCount=Integer.valueOf(request.getParameter("adultCountValue"));
		if(request.getParameter("youngCountValue").equals(""))
			youngCount=0;
		else
			youngCount=Integer.valueOf(request.getParameter("youngCountValue"));
		totalNumber = adultCount+youngCount;
		
		System.out.println("총 개수: "+totalNumber);
		
		//총 개수에 따른 선택한 좌석들을 session에 저장.
		for(int i=1; i < totalNumber+1; i++) {
			String sessionKey ="seat"+ Integer.toString(i); //seat1
			String sessionValue= sessionKey+"Value"; //seat1Value
			System.out.println(sessionKey + " " + String.valueOf(request.getParameter(sessionValue)));
			session.setAttribute(sessionKey, String.valueOf(request.getParameter(sessionValue)));
		}
		
		//티켓 아이디 가져와서 ticketId구하기
		Integer ticketId = booking2Service.getMaxTicketId();
		if(ticketId == null)
			ticketId=1;
		else
			ticketId = ticketId + 1; //insert할 ticketId
		
		String startTime = String.valueOf(session.getAttribute("startTime"));
		
		int timeId=0;
		switch (startTime) {
		case "09:00": {
			timeId=1;
			break;
		}
		case "11:00": {
			timeId=2;
			break;
		}
		case "13:00": {
			timeId=3;
			break;
		}
		case "15:00": {
			timeId=4;
			break;
		}
		case "17:00": {
			timeId=5;
			break;
		}
		case "19:00": {
			timeId=6;
			break;
		}
		default:
			timeId=0;
		}
		
		//선택한 날짜 date형식으로 구하기
		Date date = Date.valueOf(String.valueOf(session.getAttribute("date")));
	
		String movieTitle = String.valueOf(session.getAttribute("movieTitle"));
		MovieVO movieVO = booking2Service.getMovie(movieTitle);
		
		CinemaVO cinemaVO = booking2Service.getCinema(String.valueOf(session.getAttribute("cinema")));
		
		TheaterVO theaterVO = booking2Service.getTheater(cinemaVO.getCinemaId(), Integer.valueOf(String.valueOf(session.getAttribute("theater"))));

		int scheduleId = booking2Service.getScheduleId(date, timeId, movieVO.getMovieId(), cinemaVO.getCinemaId(), theaterVO.getTheaterNo());

		//session에 예매과정에 있는 값들 다 저장.
		
		//이미 저장되어 있는 것들
//		session.setAttribute("date", dateFinal);
//		session.setAttribute("movieTitle", movieTitleFinal);
//		session.setAttribute("cinema", cinemaFinal);
//		session.setAttribute("theater", theaterFinal);
//		session.setAttribute("startTime", startTimeFinal);
//		session.setAttribute("endTime", endTimeFinal);
		
		session.setAttribute("ticketId", ticketId);
		session.setAttribute("totalNumber", totalNumber);
		session.setAttribute("totalPrice", totalPrice);
		session.setAttribute("timeId", timeId);
		session.setAttribute("scheduleId", scheduleId);
		
		return "book/booking3";
	}
}
