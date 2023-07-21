package com.example.kcccinema.controller.movie;

import java.io.File;    
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.example.kcccinema.dao.IMovieRepository;
import com.example.kcccinema.model.MovieVO;


@Controller
public class MovieController {

	@Autowired
	private IMovieRepository movieRepository;
	@Autowired
	private MovieVO movie;

	/* ----- 사용자 페이지 ----- */

	/* 전체 영화 조회 */
//	@RequestMapping(value="/movies", method=RequestMethod.GET)
//	public String getMovieList(Model model) {
//		List<MovieVO> movieList = movieRepository.selectAllMovieList();
//		System.out.println(movieList);
//
//		// 추가적인 영화 데이터를 원하는 만큼 리스트에 추가할 수 있습니다.
//		/* ModelAndView modelAndView = new ModelAndView("/movie/all-movielist"); */ // movieList.html 템플릿을 사용할 것을 지정
//		model.addAttribute("movieList", movieList); // 영화 데이터를 "movieList"라는 이름으로 전달
//
//		return "movie/all-movielist";
//	}
	
	@RequestMapping(value="/movies", method=RequestMethod.GET)
	public ModelAndView getMovieList() {
	List<MovieVO> movieList = movieRepository.selectAllMovieList();
	System.out.println(movieList);

	// 추가적인 영화 데이터를 원하는 만큼 리스트에 추가할 수 있습니다.
	ModelAndView modelAndView = new ModelAndView("movie/all-movielist"); // movieList.html 템플릿을 사용할 것을 지정
	modelAndView.addObject("movieList", movieList); // 영화 데이터를 "movieList"라는 이름으로 전달

	return modelAndView;
}




}





