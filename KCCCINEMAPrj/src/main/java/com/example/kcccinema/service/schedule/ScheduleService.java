package com.example.kcccinema.service.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.dao.IScheduleRepository;

@Service
public class ScheduleService {
	
	@Autowired
	private IScheduleRepository scheduleRepository;
	
	/* 스케줄 삭제 */
	public void deleteSchedule(int scheduleId) {
		System.out.println("여기는 ScheduleService!");
		scheduleRepository.deleteSchedule(scheduleId);
		System.out.println("삭제완료!");
	}
}
