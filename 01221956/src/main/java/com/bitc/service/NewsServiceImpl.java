package com.bitc.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.common.FileUtils;
import com.bitc.dto.FileDto;
import com.bitc.dto.NewsDto;
import com.bitc.mapper.NewsMapper;

@Service
public class NewsServiceImpl implements NewsService {

	@Autowired
	private NewsMapper newsMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<NewsDto> selectNewsList() throws Exception {
		return newsMapper.selectNewsList();
	}

	@Override
	public NewsDto selectNewsDetail(int newsIdx) throws Exception {
		NewsDto news = newsMapper.selectNewsDetail(newsIdx);
		List<FileDto> fileList = newsMapper.selectNewsPhotoList(newsIdx);
		news.setFileList(fileList);
		return news;
	}
	
	@Override
	public FileDto selectFileInfo(int fileIdx, int newsIdx) throws Exception {
		return newsMapper.selectNewsFileInfo(fileIdx, newsIdx);
	}
	// 뉴스 글 작성 파일 업로드 
	@Override
	public void insertNews(NewsDto news, MultipartHttpServletRequest multiFiles) throws Exception {
		newsMapper.insertNews(news);
		
		List<FileDto> list = fileUtils.parseNewsFileInfo(news.getNewsIdx(),  multiFiles);
		
		if (CollectionUtils.isEmpty(list) == false) {
			newsMapper.insertFileList(list);
		}
	}
	// 뉴스 글 작성
//	@Override
//	public void insertNews(NewsDto news) throws Exception {
//		newsMapper.insertNews(news);

	// 뉴스 업데이트(수정)
	@Override
	public void updateNews(NewsDto news) throws Exception {
		newsMapper.updateNews(news);
	}
	// 뉴스 삭제
	@Override
	public void deleteNews(int newsIdx) throws Exception {
		newsMapper.deleteNews(newsIdx);
		}
	
	
	
}

