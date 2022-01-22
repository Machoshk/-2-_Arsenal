package com.bitc.service.tables;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.dto.TableDto;
import com.bitc.mapper.TableMapper;

@Service
public class TableServiceImpl implements TableService {

	@Autowired
	private TableMapper tableMapper;
	
	@Override
	public List<TableDto> selectTableDetail(String selectSeason) throws Exception {
		
		return tableMapper.selectTableDetail(selectSeason);
	}

}
