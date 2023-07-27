package com.example.kcccinema.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data @Getter @Setter
public class ReservedInfoVO {
	private String seat;
	private String startTime;
	private String endTime;
	private Date screenDate;
    private Long movieId;
    private String cinemaName;
    private Long theaterId;
    private String movieTitle;
    private Long    ticketId;
    private byte[] moviePoster;
    private String base64Image; // 포스터 이걸로 템플릿에 보내야 함
	
	}

