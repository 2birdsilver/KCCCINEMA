package com.example.kcccinema.model;

import java.sql.Date;
import java.util.List;

import lombok.Data;

@Data
public class ReservedInfoVO {
	private List<String> seat;
	private String startTime;
	private String endTime;
	private Date screenDate;
    private Long movieId;
    private Long cinemaId;
    private Long theaterId;
    private String movieTitle;
    private byte[] moviePoster;
    private String base64Image; // 포스터 이걸로 템플릿에 보내야 함
}
