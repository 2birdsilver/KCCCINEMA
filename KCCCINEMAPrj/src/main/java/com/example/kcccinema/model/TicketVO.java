package com.example.kcccinema.model;

import lombok.Data;

@Data
public class TicketVO {
	private int ticketId;
	private int adultNumber;
	private int childNumber;
	private String userId;
	private int scheduleId;
}
