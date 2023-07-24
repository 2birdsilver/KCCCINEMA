package com.example.kcccinema.service.movie;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.example.kcccinema.dao.IMovieRepository;
import com.example.kcccinema.model.MovieVO;

@Service
public class MovieService{
	private static final String UPLOAD_POSTER = "C:/kcccinema/movieposter/";
	@Autowired
	private IMovieRepository movieRepository;
	@Autowired
	MovieVO movie;

	/* 영화 추가 기능 */
	public void insertMovie(MovieVO movie) throws Exception {
		insertMovieInfo(movie);
		uploadPoster(movie);
	}

	public void insertMovieInfo(MovieVO movie) throws Exception {
		movieRepository.insertMovie(movie);
	}

	public void uploadPoster(MovieVO movie) {
		movieRepository.insertMoviePoster(movie);
	}


	/* 전체 영화 조회 */
	public List<MovieVO> getAllMovieList() {
		List<MovieVO> allMovieList = movieRepository.selectAllMovieList();

		Date currentDate = new Date(System.currentTimeMillis());
		for (MovieVO movie : allMovieList) {
			String base64Image = Base64.getEncoder().encodeToString(movie.getMoviePoster());
			movie.setBase64Image(base64Image);
			
			if (movie.getOpeningDate().after(currentDate)) {
				movie.setScreening("상영예정");
			} else if(movie.getClosingDate().after(currentDate)) {
				movie.setScreening("상영중");
			} else {
				movie.setScreening("상영종료");
			}
		}
		return allMovieList;
	}
	
	public List<MovieVO> getMoviesByStatus(String status) {
		List<MovieVO> movieListByStatus = new ArrayList<MovieVO>();
		List<MovieVO> allMovieList = getAllMovieList();
		for (MovieVO movie : allMovieList) {
			if(movie.getScreening().equals(status)) {
				movieListByStatus.add(movie);
			}
		}
		return movieListByStatus;
	}

	private static byte[] compressImage(byte[] imageData, double compressionQuality) {
		try (InputStream inputStream = new ByteArrayInputStream(imageData)) {
			BufferedImage originalImage = ImageIO.read(inputStream);
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

			// Create a new BufferedImage with the same dimensions and type as the original image
			BufferedImage compressedImage = new BufferedImage(originalImage.getWidth(), originalImage.getHeight(), originalImage.getType());

			// Get the Graphics2D object of the compressed image
			Graphics2D g = compressedImage.createGraphics();

			// Draw the original image on the compressed image with the specified compression quality
			g.drawImage(originalImage, 0, 0, originalImage.getWidth(), originalImage.getHeight(), null);

			// Encode the compressed image to JPEG format with the specified compression quality
			ImageIO.write(compressedImage, "jpeg", outputStream);

			return outputStream.toByteArray();
		} catch (IOException e) {
			e.printStackTrace();
			return imageData; // Return the original image data if compression fails
		}
	}

	/* 영화 검색 */
	public List<MovieVO> searchMovies(String searchWord) throws Exception{
		List<MovieVO> movieList = movieRepository.selectMoviesBySearchWord(searchWord);
		for (MovieVO movie : movieList) {
			String base64Image = Base64.getEncoder().encodeToString(movie.getMoviePoster());
			movie.setBase64Image(base64Image);
		}
		return movieList;
	}


}
