package com.example.kcccinema.model;

import java.io.FileInputStream; 
import java.sql.Date;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Component
public class MovieVO {
	private int movieId;
	private String movieTitle;
	private String movieCategory;
	private int movieScore;
	private Date openingDate;
	private Date closingDate;
	private int runningTime;
	private String movieDirector;
	private String movieSynopsis;
	private String performer;
	private String isAdultMovie; // 1이면 성인용,  0이면 전연령 시청 가능
	
	/* 이미지 파일 */
	private byte[] moviePoster;
	private String originalFilename;
	private String contentType;
	private FileInputStream fis;
	private String base64Image;
	
	// 현재 상영여부: "상영예정", "상영중", "상영종료" 3가지
	private String Screening;
	
	/*
	 * private String movieId; private String movieTitle; private String
	 * movieCategory; private LocalDate openingDate; private LocalDate closingDate;
	 * private int runningTime; private int isAdultMovie; private String
	 * movieDirector; private MultipartFile moviePoster; private String performer;
	 * private String movieSynopsis;
	 */
	
	
}
