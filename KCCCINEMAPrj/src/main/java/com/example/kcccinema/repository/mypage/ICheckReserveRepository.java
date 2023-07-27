package com.example.kcccinema.repository.mypage;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.MovieVO;
import com.example.kcccinema.model.ReservedInfoVO;
import com.example.kcccinema.vo.mypage.CheckReserveVO;

@Mapper
@Repository
public interface ICheckReserveRepository {
	CheckReserveVO	selectReserve(String movieTitle);
	void 			deleteReserve(Long ticketId);
	String			getPassword(String userId);
	List<ReservedInfoVO> getReservedInfo(String userId);
	MovieVO getMovie(Long movieId);
	void deleteSeat(Long ticketId);
}
