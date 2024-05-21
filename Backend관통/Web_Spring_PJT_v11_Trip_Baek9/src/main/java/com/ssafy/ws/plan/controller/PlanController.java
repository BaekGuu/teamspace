package com.ssafy.ws.plan.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.comment.model.Comment;
import com.ssafy.ws.plan.model.Plan;
import com.ssafy.ws.plan.model.PlanDate;
import com.ssafy.ws.plan.model.PlanDetail;
import com.ssafy.ws.plan.service.PlanService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {
	private final PlanService planService;

	// plan control

	// 모든 plan 반환
	@GetMapping("")
	public ResponseEntity<?> selectAllPlan() {
		try {
			List<Plan> list = planService.selectAllPlan();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	// 특정 member의 plan 전체 반환
	@GetMapping("/{memberId}")
	public ResponseEntity<?> selectMemberPlan(@PathVariable("memberId") String memberId) {
		try {
			List<Plan> list = planService.selectMemberPlan(memberId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 특정 plan으로 들어갔을 때 id
	@GetMapping("/detail/{planId}")
	public ResponseEntity<?> planDetail(@PathVariable("planId") int planId) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.planDetail(planId));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// plan insert
	@PostMapping("/regist")
	public ResponseEntity<?> insertPlan(@org.springframework.web.bind.annotation.RequestBody Plan plan) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.insertPlan(plan));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// plan update
	@PutMapping("/update")
	public ResponseEntity<?> updatePlan(@org.springframework.web.bind.annotation.RequestBody Plan plan) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.updatePlan(plan));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// plan delete
	@PostMapping("/delete")
	public ResponseEntity<?> deletePlan(@org.springframework.web.bind.annotation.RequestBody Plan plan) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.deletePlan(plan));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// plan 안의 day control

	// 1. plan의 날짜 수와 정보 반환
	@GetMapping("/date/{planId}")
	public ResponseEntity<?> selectPlanDate(@PathVariable("planId") int planId) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.selectPlanDate(planId));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 2. 특정 plan에 새 date 추가
	@PostMapping("/date/regist")
	public ResponseEntity<?> insertDay(@org.springframework.web.bind.annotation.RequestBody PlanDate plandate) {
		System.out.println(plandate);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.insertDay(plandate.getPlanId()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 3. 특정 plan의 date 삭제
	@PostMapping("/date/delete")
	public ResponseEntity<?> deleteDay(@org.springframework.web.bind.annotation.RequestBody PlanDate plandate) {
		System.out.println(plandate);
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.deleteDay(plandate.getId()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// date 안의 place 관련
	// 1. 특정 date 내의 위치 반환

	@GetMapping("/place/{dateId}")
	public ResponseEntity<?> placeListByDay(@PathVariable("dateId") int dateId) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.placeListByDay(dateId));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 2. 특정 date내 위치 추가
	@PostMapping("/place/regist")
	public ResponseEntity<?> insertPlaceToDay(
			@org.springframework.web.bind.annotation.RequestBody PlanDetail planDetail) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.insertPlaceToDay(planDetail));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 3. 특정 date내 위치 업데이트
	@PutMapping("/place/update")
	public ResponseEntity<?> updatePlaceToDay(
			@org.springframework.web.bind.annotation.RequestBody PlanDetail planDetail) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.updatePlaceToDay(planDetail));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 4. 특정 date내 위치 삭제
	@PostMapping("/place/delete")
	public ResponseEntity<?> deletePlaceToDay(@org.springframework.web.bind.annotation.RequestBody PlanDetail planDetail) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.deletePlaceToDay(planDetail.getId()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
	}
}
