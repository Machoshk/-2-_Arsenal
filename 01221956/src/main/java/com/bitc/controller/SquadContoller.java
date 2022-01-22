package com.bitc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.dto.SquadDto;
import com.bitc.service.squad.SquadService;

@Controller
public class SquadContoller {
	
	@Autowired
	private SquadService squadService;
	
//	선수 선택 화면 컨트롤러
	@RequestMapping(value="/team2/teams", method=RequestMethod.GET)
	public String playerList() throws Exception {
			
		return "squad/squadMember";	//선수 출력 템플릿 위치
	}
	
//	포지션 및 선수 선택 ajax 컨트롤러 
	@ResponseBody
	@RequestMapping(value="/team2/teams", method=RequestMethod.POST)
	public Object selectPlayer(@RequestParam("selectPosition") String selectPosition) throws Exception {
		
		List<String> listPlayer = new ArrayList<String>();
		
		if (selectPosition.equals("Goalkeepers")) {	//포지션 별로 선수 리스트 출력
			listPlayer.add("Bernd Leno");
			listPlayer.add("Aaron Ramsdale");
			listPlayer.add("Arthur Okonkwo");
		}
		else if (selectPosition.equals("Defenders")) {
			listPlayer.add("Kieran Tierney");
			listPlayer.add("Ben White");
			listPlayer.add("Gabriel Magalhaes");
			listPlayer.add("Rob Holding");
			listPlayer.add("Cedric Soares");
			listPlayer.add("Takehiro Tomiyasu");
			listPlayer.add("Nuno Tavares");
			listPlayer.add("Calum Chambers");
			listPlayer.add("Pablo Mari");
			listPlayer.add("Sead Kolasinac");
			
		}
		else if (selectPosition.equals("Midfielders")) {
			listPlayer.add("Thomas Partey");
			listPlayer.add("Bukayo Saka");
			listPlayer.add("Martin Odegaard");
			listPlayer.add("Emile Smith Rowe");
			listPlayer.add("Albert Lokonga");
			listPlayer.add("Mohamed Elneny");
			listPlayer.add("Granit Xhaka");
		}
		else if (selectPosition.equals("Forwards")){
			listPlayer.add("Alexandre Lacazette");
			listPlayer.add("Pierre-Emerick Aubameyang");
			listPlayer.add("Nicolas Pepe");
			listPlayer.add("Eddie Nketiah");
			listPlayer.add("Gabriel Martinelli");
		}
		
		return listPlayer;
	}
	
//	선택한 선수 상세 보기 ajax 컨트롤러(선택화면과 같은 곳에서 구현)
	@ResponseBody
	@RequestMapping(value="/team2/teamsDetail", method=RequestMethod.POST)
	public List<SquadDto> playersDetail(@RequestParam("selectPlayer") String selectPlayer) throws Exception {
		
		List<SquadDto> player = squadService.selectPlayerDetail(selectPlayer);	//sql문 -> sql-squad.xml
	
		System.out.println(player);		//dto 정보가 넘어오는지 확인 -> 나중에 지워도 됨
		
		return player;
	}
	
	  
	
////	선수 사진 인서트
//	@RequestMapping("/squad/picinput.do")
//	public String inserSquad(SquadDto squad, MultipartHttpServletRequest multiFiles) throws Exception {
//		squadService.insertSquad(squad, multiFiles);
//	
//		return "redirect:/team2/teams";
//	} 
	

	 
}
