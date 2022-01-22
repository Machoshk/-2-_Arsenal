package com.bitc.dto;

import java.util.List;

import lombok.Data;


@Data
public class BoardDto {

	private int boardIdx;
	private String title;
	private String contents;
	private String creatorId;
	private String createdDt;
	private String updatedDt;
	private int hitCnt;
	
//	첨부파일에 대한 정보를 저장하기 위한 멤버 변수를 추가함
	private List<FileDto> fileList;
}






