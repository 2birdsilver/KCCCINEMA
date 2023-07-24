package com.example.kcccinema.service.mypage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.repository.mypage.IMypageUserRepository;
import com.example.kcccinema.vo.mypage.MypageUserVO;

@Service
public class MypageService implements IMypageService{
	
	@Autowired
	IMypageUserRepository mypageUserDao;

	@Override
	public void insertUser(MypageUserVO user) {
		mypageUserDao.insertUser(user);
		
	}

	@Override
	public MypageUserVO selectUser(String userId) {
		
		return mypageUserDao.selectUser(userId);
	}

	@Override
	public void updateUser(MypageUserVO user) {
		mypageUserDao.updateUser(user);
		
	}

	@Override
	public void deleteMember(MypageUserVO user) {
		mypageUserDao.deleteMember(user);
		
	}
	


}
