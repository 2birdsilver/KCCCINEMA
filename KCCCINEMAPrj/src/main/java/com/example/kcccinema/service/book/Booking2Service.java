package com.example.kcccinema.service.book;

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
	public MovieVO getMovie(String movieTitleFinal) {
		return booking2Repository.getMovie(movieTitleFinal);
	}

	@Override
	public CinemaVO getCinema(int cinemaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TheaterVO getTheater(int theaterId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDate(String date) {
		// TODO Auto-generated method stub
		return null;
	}


}
