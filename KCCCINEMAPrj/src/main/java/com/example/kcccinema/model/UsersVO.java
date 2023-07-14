package com.example.kcccinema.model;

import java.sql.Date;

import lombok.Data;

@Data
public class UsersVO {
	private String userId;
	private String userName;
	private String userPassword;
	private int userAge;
	private Date userBirthDate;
	private int userRole;
}