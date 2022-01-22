package com.bitc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.dto.FileDto;
import com.bitc.dto.GoodsDto;

@Mapper
public interface GoodsMapper {

	List<GoodsDto> openGoodsList() throws Exception;

	void insertGoods(GoodsDto GD) throws Exception;

	void insertGoodsPhotoList(List<FileDto> list) throws Exception;

	GoodsDto selectGoodsDetail(int idx) throws Exception;

	List<FileDto> selectGoodsPhotoList(int idx) throws Exception;

	void updateGoods(GoodsDto GD) throws Exception;

	void deleteGoods(int idx) throws Exception;

	List<FileDto> openFile(int goodsIdx) throws Exception;

	List<FileDto> openPhotoList() throws Exception;

}
