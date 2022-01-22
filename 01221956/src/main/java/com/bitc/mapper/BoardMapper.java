package com.bitc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitc.dto.BoardDto;
import com.bitc.dto.FileDto;
import com.bitc.dto.NoticeDto;
import com.github.pagehelper.Page;

@Mapper
public interface BoardMapper {

	//*페이징
		Page<BoardDto> selectBoardList() throws Exception;

		Page<NoticeDto> selectNoticeList() throws Exception;

	
////	*자유게시판 글 목록 확인
//	// 일반 자유 글
//	List<BoardDto> selectBoardList() throws Exception;
//
//	// 공지 목록
//	List<NoticeDto> selectNoticeList() throws Exception;

// * 자유게시판 글 작성 하기
	// 일반 자유 글 작성
	void insertBoard(BoardDto board) throws Exception;

	// 공지글 쓰기
	void insertNotice(NoticeDto notice)throws Exception;	
	
	
// * 자유게시판 글 하나 확인하기
	// 일반 자유 글 읽기
	BoardDto selectBoardDetail(int boardIdx) throws Exception;
	
	// 공지글 읽기
	NoticeDto selectNoticeDetail(int noticeIdx) throws Exception;

//* 자유게시판 글 삭제하기
	//일반 자유 글 삭제하기
	void deleteBoard(int boardIdx) throws Exception;
	
	// 공지 글 삭제하기
	void deleteNotice(int noticeIdx) throws Exception;


//* 자유게시판 글 수정하기
	//일반 자유 글 수정하기
	void updateBoard(BoardDto board) throws Exception;
	
	// 공지 글 수정하기
	void updateNotice(NoticeDto notice) throws Exception;


	
	
//	DB에 연결하여 게시글의 첨부파일 정보를 DB에 저장하는 메서드 자유|공지
	void insertBoardFileList(List<FileDto> list) throws Exception;
	void insertNoticeFileList(List<FileDto> list) throws Exception;

	
	//<!--insertNoticeFileList//insertBoardFileList-->
//	DB에 연결하여 게시글의 첨부파일 정보를 가져오는 메서드
	List<FileDto> selectBoardFileList(int FileIdx) throws Exception;
	List<FileDto> selectNoticeFileList(int FileIdx) throws Exception;

	// 파일 업로드
	FileDto selectBoardFileInfo(@Param("fileIdx") int fileIdx, @Param("boardIdx") int boardIdx) throws Exception;
	FileDto selectNoticeFileInfo(@Param("fileIdx") int fileIdx, @Param("noticeIdx") int noticeIdx) throws Exception;

	// 조회수 올리기(일반/공지)
	void updateFreeHitCnt(int boardIdx) throws Exception;
	void updateNoticeHitCnt(int noticeIdx) throws Exception;

	//메인 인기글 보기
	List<BoardDto> selectHitCnt()throws Exception;
	


}
