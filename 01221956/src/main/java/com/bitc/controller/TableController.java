package com.bitc.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bitc.dto.TableDto;
import com.bitc.service.tables.TableService;


@Controller
public class TableController {

	@Autowired
	private TableService tableService;
	
//	순위표 출력 화면 
	@RequestMapping(value="/team2/tables", method=RequestMethod.GET)
	public String tableList() throws Exception {
		return "table/openTables";	//테이블 화면 출력 템플릿 위치
	}
	
//	리그 선택 ajax 컨트롤러
	@ResponseBody
	@RequestMapping(value="/team2/tables", method=RequestMethod.POST)
	public Object SelectTables(@RequestParam("selectLeague") String selectLeague) throws Exception {
		
		List<String> listSeason = new ArrayList<String>();
		
		if (selectLeague.equals("Epl")) {	//EPL 리그 선택시 시즌 리스트 불러옴
			listSeason.add("2019/2020");
			listSeason.add("2020/2021");
			listSeason.add("2021/2022");
		}
		
		else if (selectLeague.equals("Laliga")) {	//리그 선택시 시즌 리스트 불러옴 ( epl이랑 구분하기 위해 앞에 L붙임)
			listSeason.add("L 2019/2020");
			listSeason.add("L 2020/2021");
			listSeason.add("L 2021/2022");
		}
		return listSeason;
	}
	
//	시즌 선택 ajax 컨트롤러
	@ResponseBody
	@RequestMapping(value="team2/legueTables", method=RequestMethod.POST)
	public List<TableDto> openTables(@RequestParam("selectSeason") String selectSeason) throws Exception  {	
	
		List<TableDto> table = tableService.selectTableDetail(selectSeason);	//sql -> sql-tables.xml
		
		System.out.println(table);	// ajax success확인 ->삭제해도 됨
		return table;
	}
	
}
