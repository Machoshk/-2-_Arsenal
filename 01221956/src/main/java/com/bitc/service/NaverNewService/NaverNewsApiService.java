package com.bitc.service.NaverNewService;

import java.util.List;

import com.bitc.dto.newsApiDto.NaverNewsApiFullDataItemDto;

public interface NaverNewsApiService {

	
	List<NaverNewsApiFullDataItemDto> getItemList() throws Exception;

}
