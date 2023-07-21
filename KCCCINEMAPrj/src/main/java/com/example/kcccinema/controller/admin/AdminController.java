package com.example.kcccinema.controller.admin;

import java.io.File; 
import java.io.IOException;
import java.sql.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.kcccinema.dao.IMovieRepository;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.service.movie.MovieService;


@Controller
@RequestMapping("/admin")
public class AdminController {
	// 파일 업로드 폴더 경로
	private static final String UPLOAD_POSTER = "C:/kcccinema/movieposter/";
	@Autowired
	private IMovieRepository movieRepository;
	@Autowired
	private MovieVO movie;
	@Autowired
	private MovieService movieService;
	
	
	/* 기능1: 영화관리*/

	/* 영화 조회 */
	@RequestMapping(value="/movies", method=RequestMethod.GET)
	public String adminMovieList() {
		return "admin/movies";
	}


	/* 영화 추가 */
	
	// 영화 추가 페이지
	@RequestMapping(value="/movies/registration", method=RequestMethod.GET)
	public String insertMoviePage()  {
		return "admin/registrationform";
	}
	
	// 영화 추가 기능
	@RequestMapping(value="/movies/registration", method=RequestMethod.POST)
	public String insertMovie(@RequestParam("moviePoster") MultipartFile file, MultipartHttpServletRequest  multipartRequest, ModelAndView modelAndView) throws Exception{
		movieService.insertMovie(file, multipartRequest, modelAndView);
		return "redirect:/admin/registrationform";
	}


	
	/* 영화 수정 */
	@RequestMapping(value="/movies/{movieId}", method=RequestMethod.PUT)
	public void updateMovie()  {
		
	}
	
	/* 영화 삭제 */
	@RequestMapping(value="/movies/{movieId}", method=RequestMethod.DELETE)
	public void deleteMovie()  {
		
	}
	
	
	/* 기능2: 상영일 관리 */
	
	
	/* 상영 시간 관리 페이지 */
	@RequestMapping(value="/schedule", method=RequestMethod.GET)
	public String movieSchedule() {
		return "admin/schedule";
	}

}
