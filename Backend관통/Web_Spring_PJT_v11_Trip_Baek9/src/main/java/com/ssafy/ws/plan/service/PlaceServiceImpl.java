package com.ssafy.ws.plan.service;

import org.springframework.stereotype.Service;

import com.ssafy.ws.plan.mapper.PlanMapper;
import com.ssafy.ws.plan.model.Place;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class PlaceServiceImpl implements PlaceService{

	private final PlanMapper planMapper;
	
	@Override
	public Place selectPlace(int contentId) {
		// TODO Auto-generated method stub
		return planMapper.selectPlace(contentId);
	}

	@Override
	public int insertPlace(Place place) {
		// TODO Auto-generated method stub
		
		if(1==planMapper.insertPlace(place) && 1==planMapper.insertBarrierFree(place)) {
			return 1;
		}
		else {		
			planMapper.deletePlace(place);
			return 0;
		}
	}

	@Override
	public int updatePlace(Place place) {
		// TODO Auto-generated method stub
		Place oldData = planMapper.selectPlace(place.getContentId());
		
		if(1==planMapper.updateBarrierFree(place) && 1==planMapper.updatePlace(place)) {			
			return 1;
		}
		else {
			planMapper.updateBarrierFree(oldData);
			planMapper.updatePlace(oldData);
			return 0;
		}
	}
	
	@Override
	public int deletePlace(Place place) {
		// TODO Auto-generated method stub
		return planMapper.deletePlace(place);
	}

}
