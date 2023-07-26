package com.example.kcccinema.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.repository.mypage.ICheckReserveRepository;
import com.example.kcccinema.vo.mypage.CheckReserveVO;

@Service
public class CheckReserveService implements ICheckReserveService {

	@Autowired
	ICheckReserveRepository checkReserveDao;
	
	@Override
	public CheckReserveVO selectReserve(String movieTitle) {
		
		return checkReserveDao.selectReserve(movieTitle);
	}

	@Override
	public void deleteReserve(CheckReserveVO checkreserve) {
		checkReserveDao.deleteReserve(checkreserve);
	}

	@Override
	public String getPassword(String userId) {
		return checkReserveDao.getPassword(userId);
	}

}
