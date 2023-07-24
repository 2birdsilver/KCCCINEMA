package com.example.kcccinema.service.mypage;

import com.example.kcccinema.vo.mypage.MypageUserVO;

public interface IMypageService {
		void insertUser(MypageUserVO user);
		MypageUserVO selectUser(String userId);
		void updateUser(MypageUserVO user);
		void deleteMember(MypageUserVO user);

}
