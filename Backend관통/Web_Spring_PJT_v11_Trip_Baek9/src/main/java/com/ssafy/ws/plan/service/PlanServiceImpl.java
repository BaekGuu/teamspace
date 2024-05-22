package com.ssafy.ws.plan.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ws.plan.mapper.PlanMapper;
import com.ssafy.ws.plan.model.Place;
import com.ssafy.ws.plan.model.Plan;
import com.ssafy.ws.plan.model.PlanDate;
import com.ssafy.ws.plan.model.PlanDetail;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanServiceImpl implements PlanService {

	private final PlanMapper planMapper;
	static PlaceServiceImpl impl = null; 

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
		int cnt=planMapper.insertPlan(plan);
		if(cnt==1) {
			planMapper.insertDay(planMapper.recentPlanId());
		}
		return cnt;
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
			int priority = 1;
			for (int i = 0; i < list.size(); i++) {
				PlanDetail priorityRefactor = list.get(i);
				if(dateId==list.get(i).getDateId()) {
					priorityRefactor.setPriority(priority);
					planMapper.updatePlaceToDay(priorityRefactor);
					priority++;

					dayPlan.add(priorityRefactor);
				}
				else {
					detail.getPlanDate().add(dayPlan);
					dateId=list.get(i).getDateId();
					dayPlan = new ArrayList<>();
					
					priority=1;
					priorityRefactor.setPriority(priority);
					priority++;
					
					dayPlan.add(priorityRefactor);
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
		System.out.println(planMapper.placeListByDay(dateId));
		List<PlanDetail> list = planMapper.placeListByDay(dateId);
		
		for(int i=1;i<=list.size();i++) {
			PlanDetail temp = list.get(i-1);
			temp.setPriority(i);
			planMapper.updatePlaceToDay(temp);
		}

		System.out.println(planMapper.placeListByDay(dateId));
		
		return list;
	}

	@Override
	public int insertPlaceToDay(PlanDetail planDetail) {
		// TODO Auto-generated method stub
		System.out.println(planDetail);
		if(impl==null) {
			impl = new PlaceServiceImpl(planMapper);
		}
//		추가 전 장소 확인 후 DB 저장
		if(impl.selectPlace(planDetail.getContentId())==null) {
			impl.insertPlace(planDetail);			
		}
		else {
			impl.updatePlace(planDetail);
		}
		
		if(planDetail.getPriority()==0) {
			planDetail.setPriority(1);
		}
		if(planDetail.getDateId()==0) {
			planDetail.setDateId(planMapper.firstDay(planDetail.getPlanId()));
		}
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
