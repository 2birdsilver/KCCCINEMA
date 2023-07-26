package com.example.kcccinema.vo.mypage;

import java.io.FileInputStream;
import java.sql.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data @Getter @Setter
public class CheckReserveVO {
	//영화포스터 가져오기 
	private byte[] moviePoster;
	private String originalFilename;
	private String contentType;
	private FileInputStream fis;
	private String base64Image;
	
	
	private String	userId;
	private String 	password;
	private String 	password2;
	private String	movieTitle;
	private String	userName;
	private Date  	screenDate;
	private int		theaterId;
	private int 	seatId; 

}
