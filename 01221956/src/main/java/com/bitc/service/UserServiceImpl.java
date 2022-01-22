package com.bitc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.dto.UserDto;
import com.bitc.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserMapper userMapper;

	//멤버 확인하기
	@Override
	public int selectMemberInfoYn(String userId, String userPw) throws Exception {
		return userMapper.selectMemberInfoYn(userId, userPw);
	}

	//회원가입
	@Override
	public void insertJoin(UserDto user) throws Exception {
		userMapper.insertJoin(user);
	}

	//아이디 중복 체크
	@Override
	public int btnCheck(String userId) throws Exception {
		return userMapper.btnCheck(userId);
	}

	//회원목록 뽑아오기
	@Override
	public List<UserDto> selectUserList() throws Exception {
		return userMapper.selectUserList();
	}

	@Override
	public UserDto selectUserDetail(int userIdx) throws Exception {
		// TODO Auto-generated method stub
		return userMapper.selectUserDetail(userIdx);
	}

	@Override
	public void updateUser(UserDto user) throws Exception {
		userMapper.updateUser(user);
		
	}

	@Override
	public void userDelete(UserDto user) throws Exception {
		userMapper.userDelete(user);
		
	}




//	@Override
//	public Page<BoardDto> selectMyBoardList(int pageNum) throws Exception {
//		PageHelper.startPage(pageNum, 10);
//		return boardMapper.selectMyBoardList();
//	}
//
//	@Override
//	public Page<NoticeDto> selectMyNoticeList(int pageNum) throws Exception {
//		PageHelper.startPage(pageNum, 10);
//		return boardMapper.selectMyNoticeList();
//	}


}
