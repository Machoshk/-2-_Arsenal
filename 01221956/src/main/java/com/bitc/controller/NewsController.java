package com.bitc.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.dto.FileDto;
import com.bitc.dto.NewsDto;
import com.bitc.service.NewsService;

@Controller
public class NewsController {

	@Autowired
	private NewsService newsService;

	// @Autowired
	// private FileUtils fileUtils;

	// 뉴스 리스트 출력
	@RequestMapping(value = "/news/newsList", method = RequestMethod.GET)
	public ModelAndView selectNewsList() throws Exception {
		ModelAndView mv = new ModelAndView("/news/newsList");

		List<NewsDto> itemList = newsService.selectNewsList();
		mv.addObject("newsList", itemList);

		return mv;
	}
	
	// 뉴스 리스트 출력
	@RequestMapping(value = "/news/newsListTwo", method = RequestMethod.GET)
	public ModelAndView selectNewsList2() throws Exception {
		ModelAndView mv = new ModelAndView("/news/newsList2");

		List<NewsDto> itemList = newsService.selectNewsList();
		mv.addObject("newsList", itemList);

		return mv;
	}

	// 뉴스 상세 화면 출력
	@RequestMapping(value = "/news/openListDetail")
	public ModelAndView openListDetail(@RequestParam("newsIdx") int newsIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/news/NewsDetail2");

		NewsDto news = newsService.selectNewsDetail(newsIdx);
		mv.addObject("news", news);

		return mv;
	}

	// 뉴스 삽입
	@RequestMapping(value = "/news/newsWrite", method = RequestMethod.GET)
	public String insertNews() throws Exception {
		return "/news/newsWrite";
	}

	@RequestMapping(value = "/news/newsWrite", method = RequestMethod.POST)
	public String insertNews(NewsDto news, MultipartHttpServletRequest multiFiles) throws Exception {
		newsService.insertNews(news, multiFiles);
		return "redirect:/news/newsList";
	}

	// 뉴스 수정
	@RequestMapping("/news/updateNews.do")
	public String updateNewsList(NewsDto news) throws Exception {
		newsService.updateNews(news);
		return "redirect:/news/newsList";
	}

	@RequestMapping(value = "/news/newsInsert.do")
	public ModelAndView updateNews(@RequestParam("newsIdx") int newsIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/news/newsUpdate");
		NewsDto news = newsService.selectNewsDetail(newsIdx);
		mv.addObject("news", news);

		return mv;
	}

	// 뉴스 삭제
	@RequestMapping("/news/deleteNews.do")
	public String deleteNews(@RequestParam("newsIdx") int newsIdx) throws Exception {
		newsService.deleteNews(newsIdx);
		return "redirect:/news/newsList";
	}

	@RequestMapping("/news/downloadNewsFile")
	public void downloadNewsFile(@RequestParam int fileIdx, @RequestParam int newsIdx, HttpServletResponse response)
			throws Exception {
		FileDto boardFile = newsService.selectFileInfo(fileIdx, newsIdx);

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
}
