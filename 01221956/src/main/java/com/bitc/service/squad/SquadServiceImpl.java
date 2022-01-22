package com.bitc.service.squad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.dto.SquadDto;
import com.bitc.mapper.SquadMapper;

@Service
public class SquadServiceImpl implements SquadService {

	@Autowired
	private SquadMapper squadMapper;
	
	@Override
	public List<SquadDto> selectPlayerDetail(String selectPlayer) throws Exception {
		return squadMapper.selectPlayerDetail(selectPlayer);
	}

//	@Override
//	public void insertSquad(SquadDto squad, MultipartHttpServletRequest multiFiles) throws Exception {
//		squadMapper.insertSquad(squad);
//		List<FileDto> list = fileUtils.parseSquadFileInfo(squad.get, multiFiles);
//		if(CollectionUtils.isEmpty(list)==false) {
//			squadMapper.insertSquadPhotoList(list);
//		}
		
//	}




	

	
	


}
