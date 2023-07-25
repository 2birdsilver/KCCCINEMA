package com.example.kcccinema.controller.Book;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.json.JsonObject;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.DateVO;
import com.example.kcccinema.model.MovieTitleVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.ScheduleVO;
import com.example.kcccinema.model.TheaterVO;
import com.example.kcccinema.service.book.IBooking1Service;
import com.example.kcccinema.service.movie.IMovieService;
import com.example.kcccinema.service.movie.MovieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class Booking1Controller {
	
	@Autowired
	private IBooking1Service booking1Service;
	
	@Autowired
	private MovieService movieService;
	
	@RequestMapping("/booking1")
	public String book1(HttpSession session, Model model) {
		//예매화면 처음 들어올 때 화면
		
		//영화 객체를 리스트로 가져옴.
		List<MovieVO> movieList = movieService.getMovieListByDate();
		
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
		
		return "book/booking1";
	}
	
	@RequestMapping(value="/book/schedule", method=RequestMethod.GET)
	@ResponseBody
	public JSONObject schedule(String selectedDate, String movieTitle, String cinemaName) throws Exception{
		System.out.println(selectedDate+movieTitle+cinemaName);
		// 포맷터
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
//        String d = formatter.format(selectedDate);
        // 문자열 -> Date
        Date date = Date.valueOf(selectedDate);
        System.out.println(date);
		JSONObject schedule = new JSONObject();
		List<ScheduleVO> scheduleList = new ArrayList<ScheduleVO>();
		scheduleList = booking1Service.getScheduleList(date, movieTitle, cinemaName);
		System.out.println(scheduleList);
		schedule.put("scheduleList", scheduleList);
		System.out.println(schedule);
		return schedule;
	}
	
	
	
	@RequestMapping(value ="/booking1", method=RequestMethod.POST)
	public String book1(MovieVO movieVO, HttpSession session, Model model) {
		
		return "book/booking1";
	}
	
	@RequestMapping(value="/book/cinema", method=RequestMethod.GET)
	@ResponseBody
	public List<String> cinemaName(@RequestParam String cinemaLocation) {
		List<String> cinemaName = booking1Service.getCinemaName(cinemaLocation);
		return cinemaName;
	}
	
	@RequestMapping("/movieData")
	public String movieData(HttpServletRequest request, Model model) throws Exception {
		int mnNum = Integer.parseInt(request.getParameter("mnNum"));
		MovieVO movieData = booking1Service.getMovieData(mnNum);
		model.addAttribute("movieData", movieData);

		return "jsonView";
	}
} 
