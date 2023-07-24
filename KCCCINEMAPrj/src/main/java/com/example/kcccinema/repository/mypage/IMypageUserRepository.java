package com.example.kcccinema.repository.mypage;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.vo.mypage.MypageUserVO;

@Mapper
@Repository
public interface IMypageUserRepository {
	void insertUser(MypageUserVO user);
	MypageUserVO selectUser(String userId);
	void updateUser(MypageUserVO user);
	void deleteMember(MypageUserVO user);
}
