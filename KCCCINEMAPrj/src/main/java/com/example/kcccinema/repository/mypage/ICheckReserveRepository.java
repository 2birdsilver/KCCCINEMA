package com.example.kcccinema.repository.mypage;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.vo.mypage.CheckReserveVO;

@Mapper
@Repository
public interface ICheckReserveRepository {
	CheckReserveVO	selectReserve(String movieTitle);
	void 			deleteReserve(CheckReserveVO checkreserve);
	String			getPassword(String userId);
}
