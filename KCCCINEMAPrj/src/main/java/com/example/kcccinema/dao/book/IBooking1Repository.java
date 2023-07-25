package com.example.kcccinema.dao.book;

import java.sql.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.ScheduleVO;
import com.example.kcccinema.model.TheaterVO;

@Repository
@Mapper
public interface IBooking1Repository {
	List<MovieVO> getMovieList();
	List<CinemaVO> getCinemaList();
	MovieVO getMovieData();
	List<TheaterVO> getTheaterList();
	List<String> getCinemaName(String cinemaLocation);
	List<ScheduleVO> getScheduleList(@Param("selectedDate")Date selectedDate, @Param("movieTitle")String movieTitle, @Param("cinemaName")String cinemaName);
}
