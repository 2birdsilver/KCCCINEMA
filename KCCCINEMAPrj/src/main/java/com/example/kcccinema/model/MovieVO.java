package com.example.kcccinema.model;

import java.sql.Date;

import lombok.Data;

@Data
public class MovieVO {
	private int movieId;
	private String movieTitle;
	private String movieCategory;
	private int movieScore;
	private byte[] moviePoster;
	private Date openingDate;
	private Date closingDate;
	private int runningTime;
	private String movieDirector;
	private String movieSynopsis;
	private String performer;
}
