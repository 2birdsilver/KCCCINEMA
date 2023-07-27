package com.example.kcccinema.service.book;

import com.example.kcccinema.model.TicketVO;

public interface IBooking3Service {
	void insertTicket(TicketVO ticketVO);
	void insertSeat(int ticketId, int scheduleId, String seat);
}
