package com.ssafy.ws.plan.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.plan.model.Place;
import com.ssafy.ws.plan.model.Plan;
import com.ssafy.ws.plan.model.PlanDate;
import com.ssafy.ws.plan.model.PlanDetail;

@Mapper
public interface PlanMapper {
	//계획에 대한 CRUD
	
	//모든 plan 반환
	public List<Plan> selectAllPlan();
	// 가장 최근의 planId반환
	public int recentPlanId(); 
	//planId 입력시 가장 처음 day값 반환
	public int firstDay(int planId); 
	
	//특정 member의 plan 전체 반환
	public List<Plan> selectMemberPlan(String memberId);
	public int insertPlan(Plan plan);
	public int updatePlan(Plan plan);
	public int deletePlan(Plan plan);
	
	//planId로 plan안에 들어갈 id,memberid,plantitle,planDetail반환
	public Plan selectPlanByPlanId(int planId);
	//planId로 plan안에 들어갈 planDetail 상세정보 반환
	public List<PlanDetail> selectPlanDetailByPlanId(int planId); 
	
	
	//계획 내부 날짜 내부 방문 장소에 대한 CRUD
	public Place selectPlace(int contentId);
	
	public int insertPlace(Place place);
	public int insertBarrierFree(Place place);
	
	public int updatePlace(Place place);
	public int updateBarrierFree(Place place);
	
	public int deletePlace(Place place);

	//계획 내부 날짜에 대한 CRUD
	//plan에 해당하는 day의 수와 dayId 반환
	public List<PlanDate> selectPlanDate(int planId);
	public int insertDay(int planId);
	public int deleteDay(int id);
	
	//날짜별 방문 위치에 대한 CRUD
	//특정 date의 id를 입력시 그 날의 계획 반환
	public List<PlanDetail> placeListByDay(int dateId);
	public int insertPlaceToDay(PlanDetail planDetail);
	public int updatePlaceToDay(PlanDetail planDetail);
	public int deletePlaceToDay(int planDetailId);	
	
}
