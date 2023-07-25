package com.example.kcccinema.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.ScheduleVO;

@Mapper
@Repository
public interface IScheduleRepository {
	public void insertSchedule(ScheduleVO schedule);
}
