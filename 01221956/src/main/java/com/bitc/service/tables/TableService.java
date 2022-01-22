package com.bitc.service.tables;

import java.util.List;

import com.bitc.dto.TableDto;

public interface TableService {

	List<TableDto> selectTableDetail(String selectSeason) throws Exception;

}