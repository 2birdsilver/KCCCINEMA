package com.example.kcccinema.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ReviewVO {
	private int reviewId;
	private Date reviewDate;
	private String reviewContent;
	private int grade;
	private String userId;
	private int movieId;
}