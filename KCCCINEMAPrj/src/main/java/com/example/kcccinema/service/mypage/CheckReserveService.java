package com.example.kcccinema.service.mypage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.ReservedInfoVO;
import com.example.kcccinema.repository.mypage.ICheckReserveRepository;
import com.example.kcccinema.vo.mypage.CheckReserveVO;

@Service
public class CheckReserveService implements ICheckReserveService {

	@Autowired
	ICheckReserveRepository checkReserveRepository;
	
	@Override
	public CheckReserveVO selectReserve(String movieTitle) {
		
		return checkReserveRepository.selectReserve(movieTitle);
	}

	@Override
	public void deleteReserve(CheckReserveVO checkreserve) {
		checkReserveRepository.deleteReserve(checkreserve);
	}

	@Override
	public String getPassword(String userId) {
		return checkReserveRepository.getPassword(userId);
	}

	@Override
	public List<ReservedInfoVO> getReservedInfo(String userId) {
		return checkReserveRepository.getReservedInfo(userId);
	}

	@Override
	public MovieVO getMovie(Long movieId) {
		return checkReserveRepository.getMovie(movieId);
	}

}
