package com.example.kcccinema.controller.Book;

import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.DateVO;
import com.example.kcccinema.model.MovieTitleVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;
import com.example.kcccinema.service.book.IBooking1Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Booking1Controller {
	
	@Autowired
	private IBooking1Service booking1Service;
	
	
	@RequestMapping("/book")
	public String book1(HttpSession session, Model model) {
		//예매화면 처음 들어올 때 화면
		
		//영화 객체를 리스트로 가져옴.
		List<MovieVO> movieList = booking1Service.getMovieList();
		
		//영화 이름만 담을 리스트 생성.
		List<MovieTitleVO> movieTitleList = new ArrayList<MovieTitleVO>();
		
		Iterator<MovieVO> iterator = movieList.iterator();
		
		//영화 이름들 가져와서 리스트에 추가
		while (iterator.hasNext()) {
			MovieVO movieVO = iterator.next();
			MovieTitleVO movieTitleVO = new MovieTitleVO();
			movieTitleVO.setTitle(movieVO.getMovieTitle());
			movieTitleList.add(movieTitleVO);
		}
		//영화를 model 에 저장해서 전달.
		model.addAttribute("movieList", movieList);
		
		//영화이름들 model에 저장해서 전달.
		model.addAttribute("movieTitleList", movieTitleList);
		
		//극장 객체들 리스트로 가져와서 모델에 저장해 전달.
		List<CinemaVO> cinemaList = booking1Service.getCinemaList();
		model.addAttribute("cinemaList", cinemaList);
		
		//상영관 시간들 다 가져와서 모델에 저장해 전달.
		List<TheaterVO> theaterList = booking1Service.getTheaterList();
		model.addAttribute("theaterList", theaterList);
		
		//오늘날짜 구하기
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy,MM,dd,HH,mm");
		String[] nowTime = sdf1.format(new Date()).split(",");
		int year = Integer.parseInt(nowTime[0]);
		int month = Integer.parseInt(nowTime[1]);
		int preDay = Integer.parseInt(nowTime[2]) - 1;

		Calendar cal = Calendar.getInstance();
		cal.set(year, month, preDay);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		//상단 날짜 14개 구하기
		List<DateVO> dayWeekList = new ArrayList<DateVO>();
		String strMonth="";
		
		int cnt = 0;
		for (int i = 0; i < 15; i++) {
			DateVO dateVO = new DateVO();
			if ((preDay + i - 2) >= lastDay) {
				cnt++;
				int nextMonthDay = 0;
				if(month == 12) {
					year++;
					month = 0;
				}
				dateVO.setYear(Integer.toString(year));
			
				if((month + 1)<10) {
					strMonth = "0" + Integer.toString(month + 1);
				}
				
				dateVO.setMonth(strMonth);
				dateVO.setDay(Integer.toString(nextMonthDay + cnt));

				LocalDate date = LocalDate.of(year, month + 1, nextMonthDay + cnt);
				DayOfWeek dayOfWeek = date.getDayOfWeek();
				dateVO.setDayOfweek(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN).substring(0, 1));			
				
				dayWeekList.add(dateVO);
				continue;
			}
			dateVO.setYear(Integer.toString(year));
			
			if((month)<10) {
				strMonth = "0" + Integer.toString(month);
			}
			
			dateVO.setMonth(strMonth);
			dateVO.setDay(Integer.toString(preDay + i));

//			LocalDate date = LocalDate.of(year, month, preDay + i);
//			DayOfWeek dayOfWeek = date.getDayOfWeek();
//			dateVO.setDayOfweek(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN).substring(0, 1));			
			
			dayWeekList.add(dateVO);
		}
		
		//날짜 모델에 저장하여 전달.
		model.addAttribute("dayWeekList", dayWeekList);
		
		//시간 리스트 구하기
		List<String> timeList = new ArrayList<String>();
		String strI = "";
		for(int i=0;i<10;i++) {
			strI = Integer.toString(i);
			if(i<10) {
				strI = "0" + strI;
			}
			timeList.add(strI);
		}
		model.addAttribute("timeList", timeList);
		
		return "book/booking1";
	}
	
	@RequestMapping("/movieData")
	public String movieData(HttpServletRequest request, Model model) throws Exception {
		int mnNum = Integer.parseInt(request.getParameter("mnNum"));
		MovieVO movieData = booking1Service.getMovieData(mnNum);
		model.addAttribute("movieData", movieData);

		return "jsonView";
	}
} 
