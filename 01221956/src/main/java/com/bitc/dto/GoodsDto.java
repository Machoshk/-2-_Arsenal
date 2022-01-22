package com.bitc.dto;

import java.util.List;

import lombok.Data;

@Data
public class GoodsDto {
	private int goodsIdx;
	private String goodsTitle;
	private String goodsPrice;
	private List<FileDto> fileList;
}
