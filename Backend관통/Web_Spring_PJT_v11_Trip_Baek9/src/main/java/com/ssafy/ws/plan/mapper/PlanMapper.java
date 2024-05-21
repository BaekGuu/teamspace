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
	
	public List<Plan> selectAllPlan();//모든 plan 반환
	
	public List<Plan> selectMemberPlan(String memberId);//특정 member의 plan 전체 반환
	public int insertPlan(Plan plan);
	public int updatePlan(Plan plan);
	public int deletePlan(Plan plan);
	
	public Plan selectPlanByPlanId(int planId);//planId로 plan안에 들어갈 id,memberid,plantitle,planDetail반환
	public List<PlanDetail> selectPlanDetailByPlanId(int planId); //planId로 plan안에 들어갈 planDetail 상세정보 반환
	
	
	//계획 내부 날짜 내부 방문 장소에 대한 CRUD
	public Place selectPlace(int contentId);
	
	public int insertPlace(Place place);
	public int insertBarrierFree(Place place);
	
	public int updatePlace(Place place);
	public int updateBarrierFree(Place place);
	
	public int deletePlace(Place place);

	//계획 내부 날짜에 대한 CRUD
	public List<PlanDate> selectPlanDate(int planId);//plan에 해당하는 day의 수와 dayId 반환
	public int insertDay(int planId);
	public int deleteDay(int id);
	
	//날짜별 방문 위치에 대한 CRUD
	public List<PlanDetail> placeListByDay(int dateId);//특정 date의 id를 입력시 그 날의 계획 반환
	public int insertPlaceToDay(PlanDetail planDetail);
	public int updatePlaceToDay(PlanDetail planDetail);
	public int deletePlaceToDay(int planDetailId);	
	
}
