package com.example.kcccinema.service.movie;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.kcccinema.model.MovieVO;

public interface IMovieService {
	/* 영화 추가 */
	public void insertMovie(@RequestParam("moviePoster") MultipartFile file, MultipartHttpServletRequest  multipartRequest, ModelAndView modelAndView) throws Exception;
	public String insertMovieInfo(@RequestParam("moviePoster") MultipartFile file, MultipartHttpServletRequest  multipartRequest, ModelAndView modelAndView) throws Exception;
	public void uploadPoster(@RequestParam("moviePoster") MultipartFile file, String movieTitle );
	
	/* 상영 상태에 따른 영화 조회 */
	public List<MovieVO> getMoviesByStatus(String status);
	
	/* 영화 정보 수정 */
	public boolean updateMovieData(int movieId, String fieldName, String newValue) throws Exception;
}
