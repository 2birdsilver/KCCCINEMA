package com.example.kcccinema.dao.book;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.MovieVO;

@Repository
@Mapper
public interface IBooking2Repository {
	MovieVO getMovie(String movieTitleFinal);
}
