package com.example.kcccinema.service.book;

import java.sql.Date;
import java.util.List;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.ScheduleVO;
import com.example.kcccinema.model.TheaterVO;

public interface IBooking1Service {
	List<MovieVO> getMovieList();
	List<CinemaVO> getCinemaList();
	MovieVO getMovieData(int mnNum);
	List<TheaterVO> getTheaterList();
	List<String> getCinemaName(String cinemaLocation);
	List<ScheduleVO> getScheduleList(Date date, String movieTitle, String cinemaName);
}
