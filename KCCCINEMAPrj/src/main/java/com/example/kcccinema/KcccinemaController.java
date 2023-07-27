package com.example.kcccinema;

import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.kcccinema.dao.IMovieRepository;
import com.example.kcccinema.dao.IScheduleRepository;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.service.movie.MovieService;

@Controller
public class KcccinemaController {

	@Autowired
	private IScheduleRepository scheduleRepository;
	@Autowired
	private MovieVO movie;
	@Autowired
	private MovieService movieService;

	/* 전체 영화 조회 */

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView getMovieInfo() {
		List<MovieVO> movieList = movieService.getAllMovieList();
		List<MovieVO> movieDateList = movieService.getMovieListByDate();
		ModelAndView modelAndView = new ModelAndView("/home");
		// movieList.html 템플릿을 사용할 것을 지정

		// 추가적인 영화 데이터를 원하는 만큼 리스트에 추가
		modelAndView.addObject("movieList", movieList);
		modelAndView.addObject("movieDateList", movieDateList);
		// 영화 데이터를 "movieList"라는 이름으로 전달
		return modelAndView;
	}
}
