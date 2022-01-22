package com.bitc.service.highlight;

import java.util.List;

import com.bitc.dto.HighlightDto;

public interface HighlightService {

	List<HighlightDto> openHighlightList() throws Exception;

	void insertHighlight(HighlightDto HL) throws Exception;

	HighlightDto openHighlightDetail(int idx) throws Exception;

	void updateHighlight(HighlightDto HL) throws Exception;

	void deleteHighlight(int idx) throws Exception;



}
