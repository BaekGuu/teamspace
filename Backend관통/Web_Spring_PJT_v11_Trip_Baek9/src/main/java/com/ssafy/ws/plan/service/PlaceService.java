package com.ssafy.ws.plan.service;

import com.ssafy.ws.plan.model.Place;

public interface PlaceService {
	public Place selectPlace(int contentId);
	public int insertPlace(Place place);
	public int updatePlace(Place place);
	public int deletePlace(Place place);
}
