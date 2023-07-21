package com.example.kcccinema.dao.book;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.CinemaVO;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.TheaterVO;

@Repository
@Mapper
public interface IBooking1Repository {
	List<MovieVO> getMovieList();
	List<CinemaVO> getCinemaList();
	MovieVO getMovieData();
	List<TheaterVO> getTheaterList();
}
