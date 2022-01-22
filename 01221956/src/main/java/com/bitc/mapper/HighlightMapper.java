package com.bitc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.dto.HighlightDto;

@Mapper
public interface HighlightMapper {

	List<HighlightDto> openHighlightList() throws Exception;

	void insertHighlight(HighlightDto HL) throws Exception;

	HighlightDto openHighlightDetail(int idx) throws Exception;

	void updateHighlight(HighlightDto HL) throws Exception;

	void deleteHighlight(int idx) throws Exception;

}
