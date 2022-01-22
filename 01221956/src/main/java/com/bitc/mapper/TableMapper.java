package com.bitc.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.dto.TableDto;

@Mapper
public interface TableMapper {

	List<TableDto> selectTableDetail(String selectSeason) throws Exception;

}
