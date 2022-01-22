package com.bitc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.dto.FileDto;
import com.bitc.dto.NewsDto;

public interface NewsService {

	List<NewsDto> selectNewsList() throws Exception;

	NewsDto selectNewsDetail(int newsIdx) throws Exception;
	
	// 뉴스 작성
//	void insertNews(NewsDto news) throws Exception;
	
	// 파일 확인
	FileDto selectFileInfo(int fileIdx, int newsIdx) throws Exception;

	// 뉴스 파일 업로드
	void insertNews(NewsDto news, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception;

	// 뉴스 업데이트 (수정)
	void updateNews(NewsDto news) throws Exception;
	// 뉴스 삭제
	void deleteNews(int newsIdx) throws Exception;

}
