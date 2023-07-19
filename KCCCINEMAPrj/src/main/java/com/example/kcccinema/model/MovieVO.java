package com.example.kcccinema.model;

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
	private String isAdultMovie; // 1이면 true, 0이면 false (db랑 데이터타입 맞추기 위해서 char타입으로 설정)
	
	/* 이미지 파일 */
	private byte[] moviePoster;
	
	/*
	 * private String movieId; private String movieTitle; private String
	 * movieCategory; private LocalDate openingDate; private LocalDate closingDate;
	 * private int runningTime; private int isAdultMovie; private String
	 * movieDirector; private MultipartFile moviePoster; private String performer;
	 * private String movieSynopsis;
	 */
	
	
}
