package com.example.kcccinema.controller.movie;

import java.io.File;     
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.example.kcccinema.dao.IMovieRepository;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.service.movie.MovieService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class MovieController {

	@Autowired
	private IMovieRepository movieRepository;
	@Autowired
	private MovieVO movie;
	@Autowired
	private MovieService movieService;

	/* ----- 사용자 페이지 ----- */

	/* 전체 영화 조회 */
	@RequestMapping(value="/movies", method=RequestMethod.GET)
	public ModelAndView getMovieList() {
		List<MovieVO> movieList = movieService.getAllMovieList();
		ModelAndView modelAndView = new ModelAndView("movie/all-movielist"); // movieList.html 템플릿을 사용할 것을 지정

		// 추가적인 영화 데이터를 원하는 만큼 리스트에 추가
		modelAndView.addObject("movieList", movieList); // 영화 데이터를 "movieList"라는 이름으로 전달
		return modelAndView;
	}
	
	

	/* 영화 검색 */
	@RequestMapping(value="/movies/search" ,method = RequestMethod.GET)
	public ModelAndView searchGoods(@RequestParam("searchWord") String searchWord,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		System.out.println(searchWord);
		List<MovieVO> movieList=movieService.searchMovies(searchWord);
		System.out.println(movieList.size());
		ModelAndView mav = new ModelAndView("movie/all-movielist");
		mav.addObject("movieList", movieList);
		return mav;
	}



}





