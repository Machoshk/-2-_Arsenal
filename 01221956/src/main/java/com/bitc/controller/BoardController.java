package com.bitc.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.common.NaverNewApi;
import com.bitc.dto.BoardDto;
import com.bitc.dto.FileDto;
import com.bitc.dto.NoticeDto;
import com.bitc.dto.newsApiDto.NaverNewsApiFullDataItemDto;
import com.bitc.service.BoardService;
import com.bitc.service.NaverNewService.NaverNewsApiService;
import com.github.pagehelper.PageInfo;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Autowired
	private NaverNewsApiService naverNewsApiService;
//자유게시판||공지게시판 + 메인
	@RequestMapping("/teamTwo/main")
	public String main()throws Exception{
		//xml파일 업데이트 common>NaverNewApi
		NaverNewApi.main(null);
		//네이버 뉴스 파싱
		/*
		 * List<NaverNewsApiFullDataItemDto> itemList =
		 * naverNewsApiService.getItemList(); mv.addObject("Naver", itemList);
		 */
		return "/main";
	}
	@ResponseBody
	@RequestMapping(value = "/teamTwo/mainNews", method=RequestMethod.POST)
	public Object openNewses() throws Exception  {	
	
		List<NaverNewsApiFullDataItemDto> itemList = naverNewsApiService.getItemList();	//sql -> sql-tables.xml
		
		System.out.println(itemList);
		return itemList;
	}
	
	
//	@Autowired
//	private BoardService boardService;
//
//	// *자유 게시판 글 목록 보기 (자유글+공지글)
//	@RequestMapping("/teamTwo/freed")
//	public ModelAndView freeBoard() throws Exception {
//		ModelAndView mv = new ModelAndView("/board/freeList");
//
////		일반 사용자 | 일반 글
//		List<BoardDto> board = boardService.selectBoardList();
//		mv.addObject("board", board);
//
////		관리자 | 공지사항
//		List<NoticeDto> admin = boardService.selectNoticeList();
//		mv.addObject("admin", admin);
//
//		return mv;
//	}
	
	 @RequestMapping("/teamTwo/free") 
	 public ModelAndView paging(@RequestParam(required = false, defaultValue = "1") int pageNum) throws Exception { 
		 ModelAndView mv = new ModelAndView("/board/freeList");
		
		 PageInfo<BoardDto> board = new PageInfo<>(boardService.selectBoardList(pageNum), 3); //두번째 매개변수는 페이징 수(최대)
		 mv.addObject("board", board);
	     
		 PageInfo<NoticeDto> admin = new PageInfo<>(boardService.selectNoticeList(pageNum), 3); //두번째 매개변수는 페이징 수(최대)
	     mv.addObject("admin", admin);
		 
	     return mv; 
	}

// *자유 게시판 글쓰기
	// 일반 자유글
	@RequestMapping(value = "/teamTwo/freeWrite", method = RequestMethod.GET)
	public String insertBoard() throws Exception {
		return "/board/freeWrite";
	}

	@RequestMapping(value = "/teamTwo/freeWrite", method = RequestMethod.POST)
	public String insertBoard(BoardDto board, MultipartHttpServletRequest multiFiles) throws Exception {
		boardService.insertBoard(board, multiFiles);
		return "redirect:/teamTwo/free";
	}

	// 공지 글쓰기
	@RequestMapping(value = "/teamTwo/noticeWrite", method = RequestMethod.GET)
	public String insertNotice() throws Exception {
		return "/board/noticeWrite";
	}

	@RequestMapping(value = "/teamTwo/noticeWrite", method = RequestMethod.POST)
	public String insertNotice(NoticeDto notice, MultipartHttpServletRequest multiFiles) throws Exception {
		boardService.insertNotice(notice, multiFiles);
		return "redirect:/teamTwo/free";
	}

	// *자유 게시판 글 하나 확인하기
	// 자유 글 하나 확인
	@RequestMapping(value = "/teamTwo/free/{boardIdx}", method = RequestMethod.GET)
	public ModelAndView openBoardDetail(@PathVariable("boardIdx") int boardIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/freeDetail");
		BoardDto board = boardService.selectBoardDetail(boardIdx);
		mv.addObject("board", board);
		return mv;
	}

	// 공지글 하나 확인하기
	@RequestMapping(value = "teamTwo/notice/{noticeIdx}", method = RequestMethod.GET)
	public ModelAndView openMemberDetail(@PathVariable("noticeIdx") int noticeIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/board/noticeDetail");
		NoticeDto notice = boardService.selectNoticeDetail(noticeIdx);
		mv.addObject("notice", notice);
		return mv;
	}

// *자유 게시판 글 삭제하기
	// 자유 글 삭제하기
	@RequestMapping(value = "/teamTwo/free/{boardIdx}", method = RequestMethod.DELETE)
	public String deleteBoardList(@PathVariable("boardIdx") int boardIdx) throws Exception {
		boardService.deleteBoard(boardIdx);
		return "redirect:/teamTwo/free/";
	}

	// 공지 글 삭제하기
	@RequestMapping(value = "/teamTwo/notice/{noticeIdx}", method = RequestMethod.DELETE)
	public String deleteNoticeList(@PathVariable("noticeIdx") int noticeIdx) throws Exception {
		boardService.deleteNotice(noticeIdx);
		return "redirect:/teamTwo/free/";
	}

// *자유 게시판 글 수정하기
	// 자유 글 수정
	@RequestMapping(value = "/teamTwo/free/{boardIdx}", method = RequestMethod.PUT)
	public String updateBoardList(BoardDto board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/teamTwo/free/";
	}

	// 공지글 수정하기
	@RequestMapping(value = "/teamTwo/notice/{noticeIdx}", method = RequestMethod.PUT)
	public String updateNoticeList(NoticeDto notice) throws Exception {
		boardService.updateNotice(notice);
		return "redirect:/teamTwo/free/";
	}

	// 파일 자유게시판
	@RequestMapping("/teamTwo/free/downloadFile")
	public void downloadBoardFile(@RequestParam int fileIdx, @RequestParam int boardIdx, HttpServletResponse response)
			throws Exception {
		FileDto boardFile = boardService.selectBoardFileInfo(fileIdx, boardIdx);

		if (ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();

			byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));

			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");

			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
	
	//파일 공지게시판
	@RequestMapping("/teamTwo/notice/downloadFile")
	public void downloadNoticeFile(@RequestParam int fileIdx, @RequestParam int noticeIdx, HttpServletResponse response)
			throws Exception {
		FileDto boardFile = boardService.selectNoticeFileInfo(fileIdx, noticeIdx);

		if (ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();

			byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));

			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition",
					"attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8") + "\";");
			response.setHeader("Content-Transfer-Encoding", "binary");

			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}

// +조회수 높은 순으로 보기	
	@RequestMapping(value="/teamTwo/hitCnt", method = RequestMethod.GET)
	public ModelAndView hitCntBoard()throws Exception{
		ModelAndView mv = new ModelAndView("/main");
		List<BoardDto> hitCnt = boardService.selectHitCnt();
		mv.addObject("hit", hitCnt);
		
		return mv;
	}
	
//	*페이징 기능 넣기

	/*
	 * @RequestMapping("/board/page") public ModelAndView
	 * paging(@RequestParam(required = false, defaultValue = "1") int pageNum)
	 * throws Exception { ModelAndView mv = new ModelAndView("/board/boardList2");
	 * PageInfo<BoardDto> page = new PageInfo<>
	 * (boardService.selectEmpList(pageNum), 3); //두번째 매개변수는 페이징 수(최대)
	 * mv.addObject("num", page); return mv; }
	 */

}
