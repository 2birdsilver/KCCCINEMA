package com.example.kcccinema.model;

import lombok.Data;

@Data
public class TicketVO {
	private int ticketId;
	private int totalNumber;
	private String userId;
	private int scheduleId;
	private String startTime;
	private String endTime;
	private int totalPrice;
}
