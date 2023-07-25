package com.example.kcccinema.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.example.kcccinema.model.UsersVO;

@Mapper
@Repository
public interface IUsersRepository {
	// 회원가입
	public void userSignup(UsersVO user) throws DataAccessException;
}
