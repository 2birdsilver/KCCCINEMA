package com.example.kcccinema.service.mypage;

import java.util.List;

import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.ReservedInfoVO;
import com.example.kcccinema.vo.mypage.CheckReserveVO;

public interface ICheckReserveService {
	CheckReserveVO	selectReserve(String movieTitle);
	void 			deleteReserve(CheckReserveVO checkreserve);
	String			getPassword(String userId);
	List<ReservedInfoVO> getReservedInfo(String userid);
	MovieVO getMovie(Long movieId);

}
