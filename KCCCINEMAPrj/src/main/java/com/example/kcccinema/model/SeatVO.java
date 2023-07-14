package com.example.kcccinema.model;

import lombok.Data;

@Data
public class SeatVO {
	private int seatId;
	private int theaterId;
	private char seatRowNo;
	private int seatColNo;
}
