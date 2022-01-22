package com.bitc.service.goods;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.common.FileUtils;
import com.bitc.dto.FileDto;
import com.bitc.dto.GoodsDto;
import com.bitc.mapper.GoodsMapper;

@Service
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private FileUtils fileUtils;
	@Override
	public List<GoodsDto> openGoodsList() throws Exception {
		return goodsMapper.openGoodsList();
	}
	//굿즈 정보 입력, 굿즈 사진 추가
	@Override
	public void insertGoods(GoodsDto GD, MultipartHttpServletRequest multiFiles) throws Exception {
		goodsMapper.insertGoods(GD);
		List<FileDto> list = fileUtils.parseGoodsFileInfo(GD.getGoodsIdx(), multiFiles);
		if(CollectionUtils.isEmpty(list)==false) {
			goodsMapper.insertGoodsPhotoList(list);
		}
	}
	//idx 번호에 맞는 goods_idx, 
	@Override
	public GoodsDto selectGoodsDetail(int idx) throws Exception {
		GoodsDto GD = goodsMapper.selectGoodsDetail(idx);
		List<FileDto> fileList = goodsMapper.selectGoodsPhotoList(idx);
		GD.setFileList(fileList);
		return GD;
	}
	@Override
	public void updateGoods(GoodsDto GD) throws Exception {
		goodsMapper.updateGoods(GD);
		
	}
	@Override
	public void deleteGoods(int idx) throws Exception {
		goodsMapper.deleteGoods(idx);
		
	}
	@Override
	public List<FileDto> openFile(int goodsIdx) throws Exception {
		
		return goodsMapper.openFile(goodsIdx);
	}
	@Override
	public List<FileDto> openPhotoList() throws Exception {
		
		return goodsMapper.openPhotoList();
	}

}
