package com.bitc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.dto.HighlightDto;
import com.bitc.service.highlight.HighlightService;

@Controller
public class HighlightController {
	
	@Autowired
	private HighlightService highlightService;
	//하이라이트 메인화면
	@RequestMapping(value="/video/highlight",method=RequestMethod.GET)
	public ModelAndView goHighlight() throws Exception{
		ModelAndView mv = new ModelAndView("/highlight/highlight");
		List<HighlightDto> HL = highlightService.openHighlightList();
		mv.addObject("HL",HL);
		return mv;
	}
	//하이라이트 추가화면 전환
	@RequestMapping(value="/highlight/writeHighlight.do",method=RequestMethod.GET)
	public String writeHighlight() throws Exception{
		return "highlight/writeHighlight";
	}
	//하이라이트 추가 후 하이라이트 메인화면 이동
		// HighlightDto 에 highlightUrl은 입력양식을 예시로 html에 남겨놓음
		// youtube 공유 -> 퍼가기 -> url(iframe 안에 있는 url) 복사 해서 추가
	@RequestMapping(value="/highlight/insertHighlight", method=RequestMethod.POST)
	public String insertHighlight(HighlightDto HL) throws Exception{
		highlightService.insertHighlight(HL);
		return "redirect:/video/highlight";
	}
	// 자세히 보기 페이지
		// 관리자한테는 수정 삭제 버튼 보이게 
	@RequestMapping(value="/highlight/detail/{idx}", method=RequestMethod.POST)
	public ModelAndView openHighlightDetail(@PathVariable("idx") int idx) throws Exception{
		ModelAndView mv = new ModelAndView("/highlight/detailHighlight");
		HighlightDto HL = highlightService.openHighlightDetail(idx);
		mv.addObject("HL",HL);
		return mv;
	}
	
	@RequestMapping(value = "/highlight/edit/{idx}", method=RequestMethod.POST)
	public String updateHighlight(@PathVariable("idx") int idx, HighlightDto HL) throws Exception {
		highlightService.updateHighlight(HL);
		return "redirect:/video/highlight";
	}
	
	@RequestMapping(value = "/highlight/delete/{idx}", method = RequestMethod.POST)
	public String deleteHighlight(@PathVariable("idx") int idx) throws Exception {
		highlightService.deleteHighlight(idx);
		return "redirect:/video/highlight";
	}
	
}
