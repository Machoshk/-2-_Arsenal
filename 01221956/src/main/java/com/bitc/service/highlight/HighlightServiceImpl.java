package com.bitc.service.highlight;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.dto.HighlightDto;
import com.bitc.mapper.HighlightMapper;

@Service
public class HighlightServiceImpl implements HighlightService{

	@Autowired
	private HighlightMapper highlightMapper;
	
	@Override
	public List<HighlightDto> openHighlightList() throws Exception {
		// TODO Auto-generated method stub
		return highlightMapper.openHighlightList();
	}

	@Override
	public void insertHighlight(HighlightDto HL) throws Exception {
		highlightMapper.insertHighlight(HL);
		
	}

	@Override
	public HighlightDto openHighlightDetail(int idx) throws Exception {
		
		return highlightMapper.openHighlightDetail(idx);
	}

	@Override
	public void updateHighlight(HighlightDto HL) throws Exception {
		highlightMapper.updateHighlight(HL);
		
	}

	@Override
	public void deleteHighlight(int idx) throws Exception {
		highlightMapper.deleteHighlight(idx);
		
	}

}
