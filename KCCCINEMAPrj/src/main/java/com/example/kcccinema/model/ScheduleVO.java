package com.example.kcccinema.model;

import java.sql.Date;

import lombok.Data;

@Data
public class ScheduleVO {
	private int scheduleId;
	private Date screenDate;
	private int timeId;
	private int movieId;
	private int theaterId;
}