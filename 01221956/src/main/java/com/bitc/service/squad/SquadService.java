package com.bitc.service.squad;

import java.util.List;

import com.bitc.dto.SquadDto;

public interface SquadService {

	List<SquadDto> selectPlayerDetail(String selectPlayer) throws Exception;

//	void insertSquad(SquadDto squad, MultipartHttpServletRequest multiFiles) throws Exception;

}