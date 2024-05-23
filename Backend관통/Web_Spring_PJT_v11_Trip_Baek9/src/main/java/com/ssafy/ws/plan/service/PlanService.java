package com.ssafy.ws.plan.service;

import java.util.List;

import com.ssafy.ws.plan.model.Plan;
import com.ssafy.ws.plan.model.PlanDate;
import com.ssafy.ws.plan.model.PlanDetail;

public interface PlanService {
	
	//모든 plan 반환
	public List<Plan> selectAllPlan(); 
	//특정 member의 plan 전체 반환
	public List<Plan> selectMemberPlan(String memberId);
	public int insertPlan(Plan plan);
	public int updatePlan(Plan plan);
	public int deletePlan(Plan plan);
	
	public Plan planDetail(int planId);
	
	public List<PlanDate> selectPlanDate(int planId);
	public int insertDay(int planId);
	public int deleteDay(int id);
	
	public List<PlanDetail> placeListByDay(int dateId);
	public int insertPlaceToDay(PlanDetail planDetail);
	public int updatePlaceToDay(PlanDetail planDetail);
	public int deletePlaceToDay(int planDetailId);	
	
}
