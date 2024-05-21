package com.ssafy.ws.plan.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//place를 상속받아 어떤 날짜에 방문할 지
@Getter
@Setter

public class PlanDetail extends Place{
	private int id;
	private int planId;
	private int dateId; //어느 계획의 어느 날짜인지를 나타내는 속성
	private int priority; // 그 날짜의 계획 속 우선순위
	@Override
	public String toString() {
		return "PlanDetail [id=" + id + ", planId=" + planId + ", dateId=" + dateId + ", priority=" + priority + "] "+super.toString()+"\n";
	}
	
}