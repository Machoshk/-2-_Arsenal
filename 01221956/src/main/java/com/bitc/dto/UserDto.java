package com.bitc.dto;

import lombok.Data;

@Data
public class UserDto {

	private int userIdx;
	private String userId;
	private String userPw;
	private String email;
	private String name;
	private String phone;
	private String addr;
}
