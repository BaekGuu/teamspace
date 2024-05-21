package com.ssafy.ws.plan.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Plan {
	
	//계획에 대한 정보
	private int id;
    private String memberId;
    private String planTitle;
    private String description;
    
    //계획의 날짜별 방문 예정일 리스트로 저장해야 함.이중 List로 각 날짜별 방문 장소를 처리할 예정
    List<ArrayList<PlanDetail>> planDate = new ArrayList<>(); 
    
}
