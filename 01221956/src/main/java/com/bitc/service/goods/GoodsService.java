package com.bitc.service.goods;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.dto.FileDto;
import com.bitc.dto.GoodsDto;

public interface GoodsService {

	List<GoodsDto> openGoodsList() throws Exception;

	void insertGoods(GoodsDto GD, MultipartHttpServletRequest multiFiles) throws Exception;

	GoodsDto selectGoodsDetail(int idx) throws Exception;

	void updateGoods(GoodsDto GD) throws Exception;

	void deleteGoods(int idx) throws Exception;

	List<FileDto> openFile(int goodsIdx) throws Exception;

	List<FileDto> openPhotoList() throws Exception;

}
