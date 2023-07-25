package com.example.kcccinema.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ScheduleVO {
	private int scheduleId;
	private Date screenDate;
	private String location; // 지역
	private int time; // 시간대(1~6개)
	private int movieId;
	private String movieTitle;
	private int theaterId; // 상영관(1~3관)
	private int cinemaId; // 영화관 지점
}