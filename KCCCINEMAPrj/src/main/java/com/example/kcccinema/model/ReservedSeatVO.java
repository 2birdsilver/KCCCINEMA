package com.example.kcccinema.model;

import lombok.Data;

@Data
public class ReservedSeatVO {
	private int scheduleId;
	private int seatId;
	private int ticketId;
}
