package com.example.kcccinema.dao;

import java.util.List; 

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.example.kcccinema.model.ScheduleVO;

@Mapper
@Repository
public interface IScheduleRepository {
	/* 스케줄 등록 */
	public void insertSchedule(ScheduleVO schedule);

	/* 스케줄 조회 */
	public List<ScheduleVO> selectSchedule(ScheduleVO schedule);
	
	/* 스케줄 삭제 */
	public void deleteSchedule(int scheduleId);
}
