package com.example.kcccinema.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.UsersVO;

@Mapper
@Repository
public interface IUsersRepository {
	void insertMember(UsersVO user);

	UsersVO selectMember(String userId);

	void updateMember(UsersVO user);

	void deleteMember(UsersVO user);

	String getPassword(String userId);
}
