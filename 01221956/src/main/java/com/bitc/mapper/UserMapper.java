package com.bitc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.bitc.dto.UserDto;

@Mapper
public interface UserMapper {
	// 로그인
	int selectMemberInfoYn(@Param("userId") String userId, @Param("userPw") String userPw) throws Exception;

	// 회원가입
	void insertJoin(UserDto user) throws Exception;

	// 아이디 중복체크
	int btnCheck(@Param("userId") String userId) throws Exception;


	//회원 목록 뽑아오기
	List<UserDto> selectUserList() throws Exception;
	
	UserDto selectUserDetail(int userIdx) throws Exception;
	void updateUser(UserDto user) throws Exception;
	void userDelete(UserDto user) throws Exception;
	

	
}
