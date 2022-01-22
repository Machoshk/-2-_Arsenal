package com.bitc.dto;

import lombok.Data;

@Data
public class TableDto {
	
	private int index; //인덱스
	private String season; //시즌명
	private int teamRank;  //순위
	private String teamName; //팀명
	private int matchesPlayed; //경기수
	private int wins; //승
	private int draws;	//무
	private int losses; //패
	private int goalsScored;  //득점
	private int goalDifference;  //득실차
	private int lossesScored;	//실점
	private int getPoint;	//승점
}
