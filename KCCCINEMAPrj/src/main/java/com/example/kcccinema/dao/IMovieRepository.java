package com.example.kcccinema.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.MovieVO;

@Mapper
@Repository
public interface IMovieRepository {
	/* 영화 등록 */
	public int insertMovie(MovieVO movieVO) throws DataAccessException;

	public int insertMoviePoster(MovieVO movieVO) throws DataAccessException;

	/* 전체 영화 조회 */
	public List<MovieVO> selectAllMovieList() throws DataAccessException;

	public List<MovieVO> selectMovieListByDate() throws DataAccessException;

	/* 영화 검색 */
	public List<MovieVO> selectMoviesBySearchWord(String searchWord) throws DataAccessException;

}
