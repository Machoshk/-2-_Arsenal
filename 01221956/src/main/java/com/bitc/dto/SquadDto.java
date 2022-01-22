package com.bitc.dto;

import lombok.Data;

@Data
public class SquadDto {

	private int idx; //순번
	private String position; //포지션 
	private String name; //이름
	private String birth; //생년월일
	private String country;	//국적
	private int squadNum; 	//등번호
	private String preClubs; //이전 소속 클럽
	private String twitter;	//트위터
	
	
}
