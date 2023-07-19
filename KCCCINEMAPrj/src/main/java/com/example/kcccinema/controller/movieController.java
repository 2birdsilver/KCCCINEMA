package com.example.kcccinema.controller;

import java.io.File;    
import java.io.IOException;
import java.sql.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.example.kcccinema.dao.IMovieRepository;
import com.example.kcccinema.model.MovieVO;

@Controller
@RequestMapping("/movie")
public class movieController {
	// 파일 업로드 폴더 경로
	private static final String UPLOAD_POSTER = "C:/kcccinema/movieposter/";

	@Autowired
	private IMovieRepository movieRepository;
	@Autowired
	private MovieVO movie;



	/* 영화 추가 페이지 */
	@RequestMapping(value="/insert", method=RequestMethod.GET)
	public String insertMovie()  {
		return "movie/insertMovie";
	}

	/* 새로운 영화 등록 */
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insertMovie(MultipartHttpServletRequest  multipartRequest, ModelAndView modelAndView) throws Exception {

		multipartRequest.setCharacterEncoding("utf-8");

		// 1. 아이디
		movie.setMovieId(Integer.parseInt(multipartRequest.getParameter("movieId")));
		// 2. 제목
		movie.setMovieTitle(multipartRequest.getParameter("movieTitle"));
		// 3. 카테고리
		movie.setMovieCategory(multipartRequest.getParameter("movieCategory"));
		// 4. 상영 시작일
		Date openingDate = Date.valueOf(multipartRequest.getParameter("openingDate"));
		movie.setOpeningDate(openingDate);
		// 5. 상영 종료일
		Date closingDate = Date.valueOf(multipartRequest.getParameter("closingDate"));
		movie.setClosingDate(closingDate);
		// 6. 상영 소요시간
		movie.setRunningTime(Integer.parseInt(multipartRequest.getParameter("runningTime")));
		// 7. 영화감독
		movie.setMovieDirector(multipartRequest.getParameter("movieDirector"));
		// 8. 영화 줄거리
		movie.setMovieSynopsis(multipartRequest.getParameter("movieSynopsis"));
		// 9. 출연진
		movie.setPerformer(multipartRequest.getParameter("performer"));
		// 10. 시청연령
		movie.setIsAdultMovie(multipartRequest.getParameter("isAdultMovie"));

		System.out.println(movie);
		
		movieRepository.insertMovie(movie);
		uploadPoster(multipartRequest, movie.getMovieTitle());
		
		return "home";
	}

	/* 영화 포스터 등록 */
	public void uploadPoster(MultipartHttpServletRequest  multipartRequest, String movieTitle) throws Exception {
		multipartRequest.setCharacterEncoding("utf-8");
		MultipartFile file = multipartRequest.getFile("moviePoster");
		System.out.println("영화 포스터:" + file);
		try {
			// 파일 저장 경로 설정
			String filePath = UPLOAD_POSTER + file.getOriginalFilename();
			// 파일 저장
			file.transferTo(new File(filePath));
		} catch (IOException e) {
			System.out.println("파일로 저장 실패!");
			e.printStackTrace();
		}

		// 이미지 파일을 byte 배열로 변환
		byte[] fileBytes = null;
		try {
			fileBytes = file.getOriginalFilename().getBytes("utf-8");
		} catch (IOException e) {
			// 에러 처리
			//            return "File upload failed!";
			System.out.println("byte배열로 변환 실패!");
			e.printStackTrace();
		}

		// 파일 정보 및 바이트 배열 저장
		MovieVO movieVO = new MovieVO();
		movieVO.setMoviePoster(fileBytes);
		movieVO.setMovieTitle(movieTitle);
		System.out.println("movieVO: " + movieVO);

		movieRepository.insertMoviePoster(movieVO);

		//        return "File uploaded successfully!";
	}
}



