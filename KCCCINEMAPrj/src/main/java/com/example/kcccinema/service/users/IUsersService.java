package com.example.kcccinema.service.users;

import com.example.kcccinema.model.UsersVO;

public interface IUsersService {
	void insertMember(UsersVO user);

	UsersVO selectMember(String userId);

	void updateMember(UsersVO user);

	void deleteMember(UsersVO user);

	String getPassword(String userId);
}
