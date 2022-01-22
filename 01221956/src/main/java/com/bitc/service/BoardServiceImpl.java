package com.bitc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.common.FileUtils;
import com.bitc.dto.BoardDto;
import com.bitc.dto.FileDto;
import com.bitc.dto.NoticeDto;
import com.bitc.mapper.BoardMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardMapper boardMapper;

	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public Page<BoardDto> selectBoardList(int pageNum) throws Exception {
		PageHelper.startPage(pageNum, 10);
		return boardMapper.selectBoardList();
	}
	@Override
	public Page<NoticeDto> selectNoticeList(int pageNum) throws Exception {
		PageHelper.startPage(pageNum, 10);
		return boardMapper.selectNoticeList();
	}

////	*자유게시판 글 목록 확인
//	// 일반 자유 글
//	@Override
//	public List<BoardDto> selectBoardList() throws Exception {
//		return boardMapper.selectBoardList();
//	}
//
//	// 공지글
//	@Override
//	public List<NoticeDto> selectNoticeList() throws Exception {
//		return boardMapper.selectNoticeList();
//	}

// * 자유게시판 글 작성 하기
	// 일반 자유 글
	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multiFiles) throws Exception {
		boardMapper.insertBoard(board);

		List<FileDto> list = fileUtils.parseBoardFileInfo(board.getBoardIdx(), multiFiles);

		/* System.out.println(board.getIdx()); */

		if (CollectionUtils.isEmpty(list) == false) {
			boardMapper.insertBoardFileList(list);
		}
	}
	// 공지글

	@Override
	public void insertNotice(NoticeDto notice, MultipartHttpServletRequest multiFiles) throws Exception {
		boardMapper.insertNotice(notice);
		
		List<FileDto> list = fileUtils.parseNoticeFileInfo(notice.getNoticeIdx(), multiFiles);
		
		if (CollectionUtils.isEmpty(list) == false) {
			
			boardMapper.insertNoticeFileList(list);
		}
	}

// *자유게시판 글 확인하기
	// 일반 자유 글
	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		boardMapper.updateFreeHitCnt(boardIdx);
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		List<FileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);

		return board;

	}

	// 공지 글
	@Override
	public NoticeDto selectNoticeDetail(int noticeIdx) throws Exception {
		boardMapper.updateNoticeHitCnt(noticeIdx);
		NoticeDto notice = boardMapper.selectNoticeDetail(noticeIdx);
		List<FileDto> fileList = boardMapper.selectNoticeFileList(noticeIdx);
		notice.setFileList(fileList);
		return notice;
	}

// *자유게시판 글 삭제하기
	// 자유글
	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		boardMapper.deleteBoard(boardIdx);
	}

	// 공지글
	@Override
	public void deleteNotice(int noticeIdx) throws Exception {
		boardMapper.deleteNotice(noticeIdx);
	}

//* 자유게시판 글 수정하기
	// 자유글
	@Override
	public void updateBoard(BoardDto board) throws Exception {
		boardMapper.updateBoard(board);
	}

	// 공지글
	@Override
	public void updateNotice(NoticeDto notice) throws Exception {
		boardMapper.updateNotice(notice);
	}

//첨부파일
	//자유
	@Override
	public FileDto selectBoardFileInfo(int fileIdx, int boardIdx) throws Exception {
		return boardMapper.selectBoardFileInfo(fileIdx, boardIdx);
	}
	//공지
	@Override
	public FileDto selectNoticeFileInfo(int fileIdx, int noticeIdx) throws Exception {
		return boardMapper.selectBoardFileInfo(fileIdx, noticeIdx);
	}

	//메인 인기글 조회순으로 보기
	@Override
	public List<BoardDto> selectHitCnt() throws Exception {
		return boardMapper.selectBoardList();
	}
	




}
