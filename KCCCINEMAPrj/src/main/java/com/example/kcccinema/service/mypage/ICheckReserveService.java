package com.example.kcccinema.service.mypage;

import com.example.kcccinema.vo.mypage.CheckReserveVO;

public interface ICheckReserveService {
	CheckReserveVO	selectReserve(String movieTitle);
	void 			deleteReserve(CheckReserveVO checkreserve);
	String			getPassword(String userId);

}
