package com.bitc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.dto.FileDto;
import com.bitc.dto.GoodsDto;
import com.bitc.service.goods.GoodsService;

@Controller
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	//굿즈 메인화면
	@RequestMapping(value="/goods/main",method=RequestMethod.GET)
	public ModelAndView goGoods() throws Exception{
		ModelAndView mv = new ModelAndView("/goods/goodsMain");
		/* List<GoodsDto> GD = goodsService.openGoodsList(); */
		//사진 리스트 출력
		List<FileDto> FD = goodsService.openPhotoList();
		int arrays[] = {1, 2, 3, 4, 5};
		mv.addObject("arr", arrays);
		mv.addObject("GD",FD);
		
		return mv;
	}
	
	//쓰기화면 이동
	@RequestMapping("/goods/write.do")
	public String writeBoard() throws Exception {

		return "/goods/goodsInsertPage";
	}
	
	//쓰기화면 제출시 동작 후 메인으로 이동
	@RequestMapping("/goods/insert.do")
	public String insertBoard(GoodsDto GD, MultipartHttpServletRequest multiFiles) throws Exception {
		goodsService.insertGoods(GD, multiFiles);
	
		return "redirect:/goods/main";
	}
	//디테일 화면 오픈, 사진에 맞는 goods 정보 불러오기
	@RequestMapping("/goods/openGoodsDetail.do")
	public ModelAndView openBoardDetail(@RequestParam("goodsIdx") int goodsIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/goods/goodsDetailPage");
		GoodsDto GD = goodsService.selectGoodsDetail(goodsIdx);
		mv.addObject("GD", GD);
		
		return mv;
	}
	// 굿즈 수정
	@RequestMapping("/goods/updateGoods.do")
	public String updateBoard(GoodsDto GD) throws Exception {
		goodsService.updateGoods(GD);
		return "redirect:/goods/main";
	}
	//숫즈 삭제 (실제로 삭제는 아니고 deleted_yn='Y'
	@RequestMapping("/goods/deleteGoods.do")
	public String deleteBoard(@RequestParam("idx") int idx) throws Exception {
		goodsService.deleteGoods(idx);
		return "redirect:/goods/main";
	}
	
}
