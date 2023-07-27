package com.example.kcccinema.controller.admin;

import java.io.File;  

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.kcccinema.dao.IMovieRepository;
import com.example.kcccinema.dao.IScheduleRepository;
import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.ScheduleVO;
import com.example.kcccinema.service.movie.MovieService;
import com.example.kcccinema.service.schedule.ScheduleService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/admin")
public class AdminController {
	// 파일 업로드 폴더 경로
	private static final String UPLOAD_POSTER = "C:/kcccinema/movieposter/";
	@Autowired
	private IMovieRepository movieRepository;
	@Autowired
	private IScheduleRepository  ScheduleRepository;
	@Autowired
	private MovieVO movie;
	@Autowired
	private MovieService movieService;
	@Autowired
	private IScheduleRepository scheduleRepository;
	@Autowired
	private ScheduleService scheduleService;

	/* 상영 영화 조회 */
	@RequestMapping(value="/movies", method=RequestMethod.GET)
	public ModelAndView getMovieList() {
		List<MovieVO> movieList = movieService.getAllMovieList();
		ModelAndView modelAndView = new ModelAndView("admin/movies"); // movieList.html 템플릿을 사용할 것을 지정
		// 추가적인 영화 데이터를 원하는 만큼 리스트에 추가
		modelAndView.addObject("movieList", movieList); // 영화 데이터를 "movieList"라는 이름으로 전달
		return modelAndView;
	}


	/* 상영 상태에 따른 영화 조회 */
	@PostMapping("/movies/filterByStatus")
	@ResponseBody // 응답 데이터를 JSON 형태로 반환
	public List<MovieVO> filterMoviesByStatus(@RequestParam("status") String status) {
		List<MovieVO> movieList = new ArrayList<MovieVO>();
		if (status.equals("all-movies")) {
			movieList = movieService.getAllMovieList();
		} else {
			movieList = movieService.getMoviesByStatus(status);
		}
		return movieList;
	}

	/* 영화명 자동검색 */
	@RequestMapping(value = "/schedule/movieTitle", method = RequestMethod.GET)
	@ResponseBody
	public List<String> searchMoviesTitle(@RequestParam("keyword") String searchWord, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		List<String> movieTitleList = new ArrayList<>();
		if (searchWord.equals("") || searchWord.length() == 0) {
			return movieTitleList;
		}
		System.out.println("searchWord: " + searchWord);
		movieTitleList = movieService.searchMoviesTitle(searchWord);
		System.out.println("movieTitleList: " + movieTitleList);
		return movieTitleList;
	}

	/* 영화 정보 수정 */
	@PostMapping("/movies/updateMovieData")
	@ResponseBody
	public void updateMovieData(@RequestParam("movieId") String movieId,
			@RequestParam("fieldName") String fieldName,
			@RequestParam("newValue") String newValue) throws Exception{ 
		// movieId int타입으로 형변환
		int id = Integer.parseInt(movieId);
		if (fieldName.equals("openingDate") || fieldName.equals("closingDate")){
			// 날짜 포맷을 지정
			String pattern = "yyyy-MM-dd";

			// SimpleDateFormat을 이용하여 문자열을 Date로 변환
			SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
			try {
				java.util.Date parsedDate = dateFormat.parse(newValue);
				Date sqlDate = new Date(parsedDate.getTime());
				movieService.updateMovieData(id, fieldName, sqlDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}  else {
			String stringValue = (String) newValue;
			movieService.updateMovieData(id, fieldName, stringValue);
		}
	}





	/* 영화 추가 */

	// 영화 추가 페이지
	@RequestMapping(value="/movies/registration", method=RequestMethod.GET)
	public String insertMoviePage()  {
		return "admin/registrationform";
	}

	// 영화 추가 기능
	@RequestMapping(value="/movies/registration", method=RequestMethod.POST)
	public String insertMovie(@RequestParam("moviePoster") MultipartFile file, MultipartHttpServletRequest multipartRequest, ModelAndView modelAndView) throws Exception{
		movieService.insertMovie(file, multipartRequest, modelAndView);
		return "home";
	}


	/* 영화 수정 */
	@RequestMapping(value="/movies/{movieId}", method=RequestMethod.PUT)
	public void updateMovie()  {

	}
	/* 영화 삭제 */
	@RequestMapping(value="/movies", method=RequestMethod.DELETE)
	public void deleteMovies(@RequestBody List<Integer> selectedIds)  {
		System.out.println(selectedIds);
		movieService.deleteMovies(selectedIds);

	}


	/* 기능2: 상영일 관리 */


	/* 상영 시간 관리 페이지(/schedule) */
	@RequestMapping("/schedule")
	public String movieSchedule() {
		return "admin/schedule";


	}

	/* 스케줄 생성 */
	@PostMapping("/schedule")
	public void insertSchedule(@ModelAttribute ScheduleVO schedule) {
		System.out.println(schedule);
		ScheduleRepository.insertSchedule(schedule);
	}


	/* 스케줄 조회 */
	@ResponseBody
	@GetMapping("/schedule/{areaCode}")
	public List<ScheduleVO> selectSchedule(@ModelAttribute ScheduleVO schedule, @PathVariable String areaCode) {
		System.out.println(schedule);
		System.out.println(areaCode);
		List<ScheduleVO> selectedSchedules = scheduleRepository.selectSchedule(schedule);
		System.out.println("ajax로 보내는 데이터: " + selectedSchedules);
		return selectedSchedules;
	}

	/* 스케줄 삭제 */
	@DeleteMapping("/schedule")
	public void deleteSchedule(@RequestParam int scheduleId) {
		// 스케줄 삭제 로직을 수행합니다. (스케줄 ID를 이용하여 스케줄을 찾아서 삭제)
		System.out.println("스케줄 아이디:" + scheduleId);
		scheduleService.deleteSchedule(scheduleId);
		System.out.println("삭제 완료!");

	}


}
