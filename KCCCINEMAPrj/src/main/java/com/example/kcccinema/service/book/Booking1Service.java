package com.example.kcccinema.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.dao.book.IBooking1Repository;
import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;

@Service
public class Booking1Service implements IBooking1Service {

	@Autowired
	private IBooking1Repository booking1Repository;
	
	@Override
	public List<MovieVO> getMovieList() {
		return booking1Repository.getMovieList();
	}

	@Override
	public List<CinemaVO> getCinemaList() {
		return booking1Repository.getCinemaList();
	}

	@Override
	public MovieVO getMovieData(int mnNum) {
		return booking1Repository.getMovieData();
	}

	@Override
	public List<TheaterVO> getTheaterList() {
		return booking1Repository.getTheaterList();
	}

}
