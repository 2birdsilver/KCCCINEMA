package com.example.kcccinema.dao.book;

import java.sql.Date;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;

@Repository
@Mapper
public interface IBooking2Repository {
	MovieVO getMovie(String movieTitle);
	Integer getMaxTicketId();
	CinemaVO getCinema(String cinema);
	TheaterVO getTheater(int cinemaId, int theaterNo);
	int getScheduleId(Date date, int timeId, int movieId, int cinemaId, int theaterNo);
}
