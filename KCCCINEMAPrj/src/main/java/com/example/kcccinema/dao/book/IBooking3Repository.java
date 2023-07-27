package com.example.kcccinema.dao.book;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.TicketVO;

@Repository
@Mapper
public interface IBooking3Repository {
	void insertTicket(TicketVO ticketVO);
	void insertSeat(int ticketId, int scheduleId, String seat);
}
