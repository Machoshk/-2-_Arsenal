package com.bitc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitc.dto.FileDto;
import com.bitc.dto.NewsDto;

@Mapper
public interface NewsMapper {

	public List<NewsDto> selectNewsList() throws Exception;

	public NewsDto selectNewsDetail(int newsIdx) throws Exception;
	
//	게시글 첨부파일 db저장
	void insertFileList(List<FileDto> list) throws Exception;

//	게시글 첨부파일 정보 가져옴
	List<FileDto> selectFileList(int newsIdx) throws Exception;
	
//	파일 업로드 
	FileDto selectNewsFileInfo(@Param("fileIdx") int fileIdx, @Param("newsIdx") int newsIdx) throws Exception;

	void insertFileList(NewsDto news) throws Exception;
// 	게시글 작성
	void insertNews(NewsDto news) throws Exception;
// 	게시글 수정
	void updateNews(NewsDto news) throws Exception;

	public List<FileDto> selectNewsPhotoList(int newsIdx) throws Exception;
// 게시글 삭제
	void deleteNews(int newsIdx) throws Exception;

}
