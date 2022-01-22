package com.bitc.dto;

import java.util.List;

import lombok.Data;

@Data
public class NewsDto {

	public int newsIdx;
	public String newsTitle;
	public String newsContents;
	
	private List<FileDto> fileList;
}
