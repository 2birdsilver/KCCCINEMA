package com.example.kcccinema.service.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kcccinema.dao.IUsersRepository;
import com.example.kcccinema.model.UsersVO;

@Service
public class UsersService implements IUsersService {

	@Autowired
	private IUsersRepository usersRepository;

	public void insertUsers(UsersVO user) throws Exception {
		usersRepository.userSignup(user);
	}

}
