package com.ssafy.ws.plan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ws.plan.mapper.PlanMapper;
import com.ssafy.ws.plan.model.Plan;
import com.ssafy.ws.plan.model.PlanDate;
import com.ssafy.ws.plan.model.PlanDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

	private final PlanMapper planMapper;

	@Override
	public List<Plan> selectAllPlan() {
		// TODO Auto-generated method stub
		return planMapper.selectAllPlan();
	}

	@Override
	public List<Plan> selectMemberPlan(String memberId) {
		// TODO Auto-generated method stub
		return planMapper.selectMemberPlan(memberId);
	}

	@Override
	public int insertPlan(Plan plan) {
		// TODO Auto-generated method stub
		return planMapper.insertPlan(plan);
	}

	@Override
	public int updatePlan(Plan plan) {
		// TODO Auto-generated method stub
		return planMapper.updatePlan(plan);
	}

	@Override
	public int deletePlan(Plan plan) {
		// TODO Auto-generated method stub
		return planMapper.deletePlan(plan);
	}


	@Override
	public Plan planDetail(int planId) {
		Plan detail = planMapper.selectPlanByPlanId(planId);
		List<PlanDetail> list = planMapper.selectPlanDetailByPlanId(planId);

		if (list.size() > 0) {
			int dateId = list.get(0).getDateId();
			ArrayList<PlanDetail> dayPlan = new ArrayList<>();
			for (int i = 0; i < list.size(); i++) {
				if(dateId==list.get(i).getDateId()) {
					dayPlan.add(list.get(i));
				}
				else {
					detail.getPlanDate().add(dayPlan);
					dateId=list.get(i).getDateId();
					dayPlan = new ArrayList<>();
					dayPlan.add(list.get(i));
				}
			}
			detail.getPlanDate().add(dayPlan);
		}
		return detail;
	}

	//계획 상세 화면에서 day에 대한 crud	
	
	@Override
	public List<PlanDate> selectPlanDate(int planId) {
		// TODO Auto-generated method stub
		return planMapper.selectPlanDate(planId);
	}

	@Override
	public int insertDay(int planId) {
		// TODO Auto-generated method stub
		return planMapper.insertDay(planId);
	}

	@Override
	public int deleteDay(int id) {
		// TODO Auto-generated method stub
		return planMapper.deleteDay(id);
	}

	//날짜별 방문 위치에 대한 crud
	
	@Override
	public List<PlanDetail> placeListByDay(int dateId) {
		// TODO Auto-generated method stub
		return planMapper.placeListByDay(dateId);
	}

	@Override
	public int insertPlaceToDay(PlanDetail planDetail) {
		// TODO Auto-generated method stub
		return planMapper.insertPlaceToDay(planDetail);
	}

	@Override
	public int updatePlaceToDay(PlanDetail planDetail) {
		// TODO Auto-generated method stub
		return planMapper.updatePlaceToDay(planDetail);
	}

	@Override
	public int deletePlaceToDay(int planDetailId) {
		// TODO Auto-generated method stub
		return planMapper.deletePlaceToDay(planDetailId);
	}

}
