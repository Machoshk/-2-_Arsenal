package com.bitc.common;

import java.io.File;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bitc.dto.FileDto;

@Component
public class FileUtils {
//	자유게시판+공지 / 뉴스 / 굿즈

//	자유게시판
	public List<FileDto> parseBoardFileInfo(int boardIdx, MultipartHttpServletRequest multiFiles) throws Exception {

		if (ObjectUtils.isEmpty(multiFiles)) {
			return null;
		}

		List<FileDto> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "boardImages/" + current.format(format);

		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs(); 
		}

		Iterator<String> iterator = multiFiles.getFileNames();
		String newFileName;
		String originalFileExtension;
		String contentType;

		while (iterator.hasNext()) {
			String name = iterator.next();
			List<MultipartFile> list = multiFiles.getFiles(name);

			for (MultipartFile mFile : list) {
				if (mFile.isEmpty() == false) {
					contentType = mFile.getContentType();
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}

					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

					FileDto boardFile = new FileDto();
					boardFile.setBoardIdx(boardIdx);
					
					boardFile.setFileSize(Long.toString(mFile.getSize()));
					boardFile.setOriginalFileName(mFile.getOriginalFilename());
					boardFile.setStoredFilePath(path + "/" + newFileName);

					fileList.add(boardFile);

					file = new File(path + "/" + newFileName);
					mFile.transferTo(file);
				}
			}
		}

		return fileList;
	}
	
//공지게시판	
	public List<FileDto> parseNoticeFileInfo(int noticeIdx, MultipartHttpServletRequest multiFiles) throws Exception {

		if (ObjectUtils.isEmpty(multiFiles)) {
			return null;
		}

		List<FileDto> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "noticeImages/" + current.format(format);

		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs(); 
		}

		Iterator<String> iterator = multiFiles.getFileNames();
		String newFileName;
		String originalFileExtension;
		String contentType;

		while (iterator.hasNext()) {
			String name = iterator.next();
			List<MultipartFile> list = multiFiles.getFiles(name);

			for (MultipartFile mFile : list) {
				if (mFile.isEmpty() == false) {
					contentType = mFile.getContentType();
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}


					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

					FileDto noticeFile = new FileDto();
					noticeFile.setNoticeIdx(noticeIdx);
					
					noticeFile.setFileSize(Long.toString(mFile.getSize()));
					noticeFile.setOriginalFileName(mFile.getOriginalFilename());
					noticeFile.setStoredFilePath(path + "/" + newFileName);

					fileList.add(noticeFile);

					file = new File(path + "/" + newFileName);
					mFile.transferTo(file);
				}
			}
		}

		return fileList;
	}
	

//뉴스 게시판
	public List<FileDto> parseNewsFileInfo(int goodsIdx, MultipartHttpServletRequest multiFiles) throws Exception {

		if (ObjectUtils.isEmpty(multiFiles)) {
			return null;
		}

		List<FileDto> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "newsImages/" + current.format(format);

		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs(); 
		}

		Iterator<String> iterator = multiFiles.getFileNames();
		String newFileName;
		String originalFileExtension;
		String contentType;

		while (iterator.hasNext()) {
			String name = iterator.next();
			List<MultipartFile> list = multiFiles.getFiles(name);

			for (MultipartFile mFile : list) {
				if (mFile.isEmpty() == false) {
					contentType = mFile.getContentType();
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}


					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

					FileDto newsFile = new FileDto();
					newsFile.setNewsIdx(goodsIdx);
					
					newsFile.setFileSize(Long.toString(mFile.getSize()));
					newsFile.setOriginalFileName(mFile.getOriginalFilename());
					newsFile.setStoredFilePath(path + "/" + newFileName);

					fileList.add(newsFile);

					file = new File(path + "/" + newFileName);
					mFile.transferTo(file);
				}
			}
		}

		return fileList;
	}
	

//굿즈 게시판	
	public List<FileDto> parseGoodsFileInfo(int goodsIdx, MultipartHttpServletRequest multiFiles) throws Exception {

		if (ObjectUtils.isEmpty(multiFiles)) {
			return null;
		}

		List<FileDto> fileList = new ArrayList<>();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
		ZonedDateTime current = ZonedDateTime.now();
		String path = "goodsImages/" + current.format(format);

		File file = new File(path);
		if (file.exists() == false) {
			file.mkdirs(); 
		}

		Iterator<String> iterator = multiFiles.getFileNames();
		String newFileName;
		String originalFileExtension;
		String contentType;

		while (iterator.hasNext()) {
			String name = iterator.next();
			List<MultipartFile> list = multiFiles.getFiles(name);

			for (MultipartFile mFile : list) {
				if (mFile.isEmpty() == false) {
					contentType = mFile.getContentType();
					if (ObjectUtils.isEmpty(contentType)) {
						break;
					} else {
						if (contentType.contains("image/jpeg")) {
							originalFileExtension = ".jpg";
						} else if (contentType.contains("image/png")) {
							originalFileExtension = ".png";
						} else if (contentType.contains("image/gif")) {
							originalFileExtension = ".gif";
						} else {
							break;
						}
					}


					newFileName = Long.toString(System.nanoTime()) + originalFileExtension;

					FileDto goodsFile = new FileDto();
					goodsFile.setGoodsIdx(goodsIdx);
					
					goodsFile.setFileSize(Long.toString(mFile.getSize()));
					goodsFile.setOriginalFileName(mFile.getOriginalFilename());
					goodsFile.setStoredFilePath(path + "/" + newFileName);

					fileList.add(goodsFile);

					file = new File(path + "/" + newFileName);
					mFile.transferTo(file);
				}
			}
		}

		return fileList;
	}
}
