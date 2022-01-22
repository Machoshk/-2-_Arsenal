package com.bitc.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.bitc.dto.FileDto;
import com.bitc.dto.SquadDto;


@Mapper
public interface SquadMapper {

	List<SquadDto> selectPlayerDetail(String selectPlayer) throws Exception;

	void insertSquad(SquadDto squad);

	void insertSquadPhotoList(List<FileDto> list);

}
