package com.bitc.dto;


import lombok.Data;

@Data
public class FileDto {
	private int fileIdx;
	private int boardIdx;
	private int noticeIdx;
	private int newsIdx;
	private int goodsIdx;
	
	private String originalFileName;
	private String storedFilePath;
	private String fileSize;
}
