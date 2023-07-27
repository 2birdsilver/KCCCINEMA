package com.example.kcccinema.service.book;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.dao.book.IBooking3Repository;
import com.example.kcccinema.model.TicketVO;

@Service
public class Booking3Service implements IBooking3Service {
	@Autowired
	IBooking3Repository booking3Repository;
	
	@Override
	public void insertTicket(TicketVO ticketVO) {
		booking3Repository.insertTicket(ticketVO);
	}

	@Override
	public void insertSeat(int ticketId, int scheduleId, String seat) {
		booking3Repository.insertSeat(ticketId, scheduleId, seat);
	}
}
