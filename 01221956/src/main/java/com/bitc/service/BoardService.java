package com.bitc.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.dto.BoardDto;
import com.bitc.dto.FileDto;
import com.bitc.dto.NoticeDto;
import com.github.pagehelper.Page;

public interface BoardService {

//	일반 게시판 페이징
	Page<BoardDto> selectBoardList(int pageNum) throws Exception;
//	관리자 게시판 페이징
	Page<NoticeDto> selectNoticeList(int pageNum) throws Exception;
	
	
////	*자유게시판 글 목록 확인
//	// 일반 자유 글
//	List<BoardDto> selectBoardList() throws Exception;
//
//	// 관리자의 공지글
//	List<NoticeDto> selectNoticeList() throws Exception;

// * 자유게시판 글 작성 하기
	// 일반 자유 글 작성
	void insertBoard(BoardDto board, MultipartHttpServletRequest multiFiles) throws Exception;

	// 공지글 쓰기
	void insertNotice(NoticeDto notice, MultipartHttpServletRequest multiFiles) throws Exception;

// * 자유게시판 글 하나 확인하기
	// 일반 자유 글 읽기
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	// 공지글 읽기
	NoticeDto selectNoticeDetail(int noticeIdx) throws Exception;

// * 자유게시판 글 하나 삭제하기
	
	// 일반 자유 글 삭제하기
	void deleteBoard(int boardIdx) throws Exception;
	
	// 공지 글 삭제하기
	void deleteNotice(int noticeIdx) throws Exception;

//* 자유게시판 글 수정하기
	// 일반 자유 글 수정하기
	void updateBoard(BoardDto board) throws Exception;
	
	// 공지 글 수정하기
	void updateNotice(NoticeDto notice) throws Exception;

// 첨부파일
	// 파일 확인 하기
	FileDto selectBoardFileInfo(int fileIdx, int boardIdx) throws Exception;
	FileDto selectNoticeFileInfo(int fileIdx, int noticeIdx) throws Exception;

	//조회순으로 보기
	List<BoardDto> selectHitCnt()throws Exception;



}
