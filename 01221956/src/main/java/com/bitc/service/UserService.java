package com.bitc.service;

import java.util.List;

import com.bitc.dto.UserDto;

public interface UserService {

	// 로그인 페이지~
	int selectMemberInfoYn(String userId, String userPw) throws Exception;

	//회원가입
	void insertJoin(UserDto user)throws Exception;

	//아이디 중복체크

	int btnCheck(String userId) throws Exception;


	//회원 목록 뽑아오기
	List<UserDto> selectUserList() throws Exception;
	
	//회원

	UserDto selectUserDetail(int userIdx) throws Exception;
	void updateUser(UserDto user) throws Exception;
	void userDelete(UserDto user) throws Exception;




}
