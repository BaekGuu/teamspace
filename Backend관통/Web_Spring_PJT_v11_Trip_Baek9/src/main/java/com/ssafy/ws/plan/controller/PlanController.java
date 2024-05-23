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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/plan")
public class PlanController {
	private final PlanService planService;

	// plan control

	// 모든 plan 반환
	@GetMapping("")
	@Operation(summary = "모든 plan 반환",description = "모든 plan 반환")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "모든 plan 반환")
	})
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
	@Operation(summary = "특정 member의 plan 전체 반환",description = "memberId 필요. 로그인시 id값 입력하면 됨")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "특정 member의 plan 반환")
	})
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
	@Operation(summary = "특정 plan으로 들어갔을 때 plan의 상세 정보 반환",description = "plan의 id값이 필요")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "plan_id에 해당하는 plan의 상세정보 반환")
	})
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
	@Operation(summary = "plan insert",description = "member_id,plan_title,description 필요")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0")
	})
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
	@Operation(summary = "plan update",description = "원본 memberId,id 필요. 바꿀 plan_title,description 필요")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0")
	})
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
	@Operation(summary = "plan delete",description = "삭제할 plan의 id값,memberId 필요")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0")
	})
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
	@Operation(summary = "plan의 날짜 수와 정보 반환",description = "plan id를 입력하면, 해당 plan의 day가 day의 id로 반환됨"
			+ ". 예를들어, planId인 1을 입력하면 (planId,id)=(1,5),(1,7)이 반환되면 plan id가 1인 plan은 2일차까지 있고, 각 day의 id가 5,7")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "plan id의 날짜 id가 반환됨")
	})
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
	@Operation(summary = "특정 plan에 새 date 추가",description = "plan id를 입력하면, 해당 plan의 day가 추가됨")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "plan id의 날짜 id가 반환됨")
	})
	@PostMapping("/date/regist")
	public ResponseEntity<?> insertDay(@org.springframework.web.bind.annotation.RequestBody PlanDate plandate) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(planService.insertDay(plandate.getPlanId()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// 3. 특정 plan의 date 삭제
	@Operation(summary = "특정 plan의 date 삭제",description = "plan의 date의 id를 입력하면 삭제됨")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공 1, 실패 0")
	})
	@PostMapping("/date/delete")
	public ResponseEntity<?> deleteDay(@org.springframework.web.bind.annotation.RequestBody PlanDate plandate) {
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
	@Operation(summary = "특정 plan의 특정 date 내의 방문 예정 장소들 반환",description = "date의 id를 입력하면, 해당 날에 방문 예정인 위치 반환")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "특정 날짜 방문 예정 위치 리스트 반환")
	})
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
	@Operation(summary = "특정 plan의 특정 date 내의 방문 예정 장소 추가",description = "content_id,date_id,priority필요. content_id는 방문 예정 장소 id, date_id는 방문예정 날짜 id, priority는 방문 순서. 낮을수록 먼저 방문. date_id를 입력하지 않을 경우, planId를 전송하면 해당 계획의"
			+ "맨 처음 day에 저장됨")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공 1 실패 0")
	})
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
	@Operation(summary = "특정 plan의 특정 date 내의 방문 예정 장소 변경",description = "id,dateId,priority값 필요. 방문 날짜를 변경할 예정이면 dateId를 해당 일자의 dateId로 바꾸고, priority로 방문 순위 변경. id값은 그대로 사용해야 한다(방문 예정 장소의 식별을 위한 id값임)")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공 1 실패 0")
	})
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
	@Operation(summary = "특정 plan의 특정 date 내의 방문 예정 장소 삭제",description = "id값만 있으면 삭제 가능. 여기서 id값은 특정 날짜 내의 특정 장소 id값임")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공 1 실패 0")
	})
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
