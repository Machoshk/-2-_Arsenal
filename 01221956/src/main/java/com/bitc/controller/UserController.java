
package com.bitc.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.bitc.dto.UserDto;
import com.bitc.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
//회원가입+로그인+마이페이지(회원정보 수정)

// *회원가입 페이지
	@RequestMapping(value = "/teamTwo/join", method = RequestMethod.GET)
	public String userR() throws Exception {
		return "/log/join";
	}

	@RequestMapping(value = "/teamTwo/join", method = RequestMethod.POST)
	public String userR(UserDto user) throws Exception {
		userService.insertJoin(user);
		return "redirect:/teamTwo/login";
	}

	// 회원가입 ajax ->연락처 관련
	@ResponseBody
	@RequestMapping(value = "/teamTwo/joinTel", method = RequestMethod.POST)
	public Object userRe(@RequestParam("tel") String tel) throws Exception {
		List<String> telList = new ArrayList<String>();

		if (tel.equals("핸드폰번호")) {
			telList.add("010");
			telList.add("017");
			telList.add("011");

		} else if (tel.equals("지역번호")) {
			telList.add("02");
			telList.add("042");
			telList.add("051");
		}
		return telList;
	}

	//중복아이디 체크
	@ResponseBody
	@RequestMapping(value = "/teamTwo/joinIdCheck", method = RequestMethod.POST)
	public boolean btnCheck(@RequestParam("userId") String userId) throws Exception {
		int count = userService.btnCheck(userId);

		if (count==0) {
			return false;
		} else {
			return true;
		}
	}
// *로그인 페이지 시작
	// 로그인 페이지
	@RequestMapping(value = "teamTwo/login", method = RequestMethod.GET)
	public String login() throws Exception {
		return "/log/login";
	}

	@RequestMapping(value = "teamTwo/loginCheck", method = RequestMethod.POST)
	public String loginCheck(UserDto user, HttpServletRequest request) throws Exception {
		int count = userService.selectMemberInfoYn(user.getUserId(), user.getUserPw());

		if (count == 1) {
			HttpSession session = request.getSession();
			session.setAttribute("userId", user.getUserId());
			session.setMaxInactiveInterval(30);
			// ?후에 메인페이지로 변경하기
			return "redirect:/teamTwo/main";
		} else {
			//
			return "redirect:/teamTwo/loginFail";
		}
	}

	// 로그아웃
	@RequestMapping(value = "/teamTwo/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request) throws Exception {
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.invalidate();

		return "/log/login";
	}

	// 로그인 실패
	@RequestMapping(value = "/teamTwo/loginFail", method = RequestMethod.GET)
	public String loginFail() throws Exception {
		return "/log/loginFail";
	}
	
	
	//**마이페이지
	@RequestMapping(value="/teamTwo/myPage")
	public ModelAndView myPage()throws Exception{
		ModelAndView mv = new ModelAndView("/myPage/myPage");
		
//		List<BoardDto> board = myPageService.selectMyBoardList();
//		mv.addObject("board", board);
		
		List<UserDto> user = userService.selectUserList();
		mv.addObject("user", user);
		
		mv.addObject(mv);
		return mv;
	}
	
	//* 회원정보 수정하기
	// 유저 하나 확인
	@RequestMapping(value = "/teamTwo/userEdit/{userIdx}", method = RequestMethod.GET)
	public ModelAndView openBoardDetail(@PathVariable("userIdx") int userIdx) throws Exception {
		ModelAndView mv = new ModelAndView("/myPage/userEdit");
		UserDto user = userService.selectUserDetail(userIdx);
		mv.addObject("user", user);
		return mv;
	}
	
	
	//* 회원정보 수정하기
	@RequestMapping(value = "/teamTwo/userEdit/{userIdx}", method = RequestMethod.PUT)
	public String updateUserList(UserDto user) throws Exception {
		userService.updateUser(user);
		return "redirect:/teamTwo/myPage/";
	}
	
	//* 회원정보 삭제하기
	@RequestMapping(value = "/teamTwo/userEdit/{userIdx}", method = RequestMethod.DELETE)
	public String UserDelete(UserDto user,HttpServletRequest request) throws Exception {
		
//		회원정보 삭제후에도 로그인이 유지되는 상황 방지...
		userService.userDelete(user);
		HttpSession session = request.getSession();
		session.removeAttribute("userId");
		session.invalidate();
		
		return "redirect:/teamTwo/main/";
	}
	
	


//	* 회원전체 보기
//	@RequestMapping("/teamTwo/userList")
//	public ModelAndView freeBoard() throws Exception {
//		ModelAndView mv = new ModelAndView("/user/userList");
//		List<UserDto> user = userService.selectUserList();
//		mv.addObject("user", user);
//
//		return mv;
//	}
	

	
	/*
	 * //유저 하나 수정
	 * 
	 * @RequestMapping(value = "/teamTwo/user/{userIdx}", method =
	 * RequestMethod.PUT) public String updateBoardList(UserDto user) throws
	 * Exception { userService.updateUser(user); return
	 * "redirect:/teamTwo/userList"; }
	 */
//*회원정보 수정 보기

	/*
	 * //회원정보 수정!
	 * 
	 * @RequestMapping(value = "/teamTwo/userEdit/{userIdx}", method =
	 * RequestMethod.PUT) public String updateBoardList(UserDto user) throws
	 * Exception { UserService.updateUser(user); return "redirect:/teamTwo/myPage";
	 * }
	 */

}