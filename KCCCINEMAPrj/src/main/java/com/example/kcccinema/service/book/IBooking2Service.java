package com.example.kcccinema.service.book;

import java.sql.Date;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;

public interface IBooking2Service {
	MovieVO getMovie(String movieTitle);
	Integer getMaxTicketId();
	CinemaVO getCinema(String cinema);
	TheaterVO getTheater(int cinemaId, int theaterNo);
	int getScheduleId(Date date, int timeId, int movieId, int cinemaId, int theaterNo);

}
