package com.example.kcccinema.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.MovieVO;

@Mapper
@Repository
public interface IMovieRepository {
	public int insertMovie(MovieVO movieVO) throws DataAccessException;
	public int insertMoviePoster(MovieVO movieVO) throws DataAccessException;

}
