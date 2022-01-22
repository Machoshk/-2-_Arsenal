package com.bitc.service.NaverNewService;

import java.io.File;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.springframework.stereotype.Service;

import com.bitc.dto.newsApiDto.NaverNewsApiFullDataItemDto;
import com.bitc.dto.newsApiDto.NaverNewsApiFullDataRssDto;
import com.bitc.dto.newsApiDto.NaverNewsApiFulldataDto;

@Service
public class NaverNewsApiServiceImpl implements NaverNewsApiService{
	
	@Override
	public List<NaverNewsApiFullDataItemDto> getItemList() throws Exception {
		System.out.println(1);
		JAXBContext jc = JAXBContext.newInstance(NaverNewsApiFullDataRssDto.class);
		System.out.println(2);		
		Unmarshaller um=jc.createUnmarshaller();
		System.out.println(3);		
		//xml 파일 경로
		NaverNewsApiFullDataRssDto channel = (NaverNewsApiFullDataRssDto)um.unmarshal(new File("C://spring//01221956//네이버뉴스.xml"));
		NaverNewsApiFulldataDto fullData=channel.getChannel();
		List<NaverNewsApiFullDataItemDto> itemList = fullData.getItem();
		return itemList;
	}


	
	
}
