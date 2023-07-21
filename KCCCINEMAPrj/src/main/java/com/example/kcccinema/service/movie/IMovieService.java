package com.example.kcccinema.service.movie;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

public interface IMovieService {
	/* 영화 추가 */
	public void insertMovie(@RequestParam("moviePoster") MultipartFile file, MultipartHttpServletRequest  multipartRequest, ModelAndView modelAndView) throws Exception;
	public String insertMovieInfo(@RequestParam("moviePoster") MultipartFile file, MultipartHttpServletRequest  multipartRequest, ModelAndView modelAndView) throws Exception;
	public void uploadPoster(@RequestParam("moviePoster") MultipartFile file, String movieTitle );
	
	
}
