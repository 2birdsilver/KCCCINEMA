package com.example.kcccinema.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ScheduleVO {
	private Integer scheduleId;
	private Date screenDate;
	private String location; // 지역
	private Integer time; // 시간대(1~6개)
	private Integer movieId;
	private String movieTitle;
	private Integer theaterId; // 상영관(1~3관)
	private Integer cinemaId; // 영화관 지점
}