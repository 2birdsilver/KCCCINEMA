package com.example.kcccinema.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.dao.IUsersRepository;
import com.example.kcccinema.model.UsersVO;

@Service
public class UsersService implements IUsersService {

	@Autowired
	private IUsersRepository usersRepository;

	@Override
	public void insertMember(UsersVO user) {
		usersRepository.insertMember(user);
	}

	@Override
	public UsersVO selectMember(String userId) {
		return usersRepository.selectMember(userId);
	}

	@Override
	public void updateMember(UsersVO user) {
		usersRepository.updateMember(user);
	}

	@Override
	public void deleteMember(UsersVO user) {
		usersRepository.deleteMember(user);
	}

	@Override
	public String getPassword(String userId) {
		return usersRepository.getPassword(userId);
	}

}
