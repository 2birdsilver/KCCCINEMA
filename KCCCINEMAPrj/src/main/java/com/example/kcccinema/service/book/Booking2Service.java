package com.example.kcccinema.service.book;

import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.dao.book.IBooking2Repository;
import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;

@Service
public class Booking2Service implements IBooking2Service {

	@Autowired
	private IBooking2Repository booking2Repository;
	
	@Override
	public MovieVO getMovie(String movieTitle) {
		return booking2Repository.getMovie(movieTitle);
	}

	@Override
	public Integer getMaxTicketId() {
		return booking2Repository.getMaxTicketId();
	}

	@Override
	public CinemaVO getCinema(String cinema) {
		return booking2Repository.getCinema(cinema);
	}

	@Override
	public TheaterVO getTheater(int cinemaId, int theaterNo) {
		return booking2Repository.getTheater(cinemaId, theaterNo);
	}

	@Override
	public int getScheduleId(Date date, int timeId, int movieId, int cinemaId, int theaterNo) {
		return booking2Repository.getScheduleId(date, timeId, movieId, cinemaId, theaterNo);
	}
}
