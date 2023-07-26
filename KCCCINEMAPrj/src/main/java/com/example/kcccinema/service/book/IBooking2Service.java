package com.example.kcccinema.service.book;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;

public interface IBooking2Service {
	MovieVO getMovie(String movieTitleFinal);
	CinemaVO getCinema(int cinemaId);
	TheaterVO getTheater(int theaterId);
	String getDate(String date);

}
