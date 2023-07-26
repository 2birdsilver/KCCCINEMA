package com.example.kcccinema.vo.mypage;

import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class MypageUserVO {
	private String userId;
	private String userName;
	private int    userAge;
	private Date   userBirthDate;
	private String password;

}
