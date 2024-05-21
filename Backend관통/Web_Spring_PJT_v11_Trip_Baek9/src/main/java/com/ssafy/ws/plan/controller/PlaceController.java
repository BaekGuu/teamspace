package com.ssafy.ws.plan.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.plan.model.Place;
import com.ssafy.ws.plan.service.PlaceService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/place")
public class PlaceController {
	private final PlaceService placeService;

	// contentId로 장소검색
	@GetMapping("/{contentId}")
	@Operation(summary = "contentId로 장소 검색",description = "장소")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "장소 정보 반환")
	})
	public ResponseEntity<?> selectPlace(@PathVariable("contentId") int contentId) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(placeService.selectPlace(contentId));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// place insert
	@Operation(summary = "위치 정보 삽입",description = "위치 정보 삽입")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0 반환")
	})
	@PostMapping("/regist")
	public ResponseEntity<?> insertPlan(@org.springframework.web.bind.annotation.RequestBody Place place) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(placeService.insertPlace(place));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// place update
	@Operation(summary = "위치 정보 업데이트.",description = "위치 정보 업데이트. 안 바꿀 정보도 다 기존 데이터 작성해서 보내야함")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0 반환")
	})
	@PutMapping("/update")
	public ResponseEntity<?> updatePlan(@org.springframework.web.bind.annotation.RequestBody Place place) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(placeService.updatePlace(place));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	// place delete
	@Operation(summary = "위치 정보 삭제. contentId만 있으면 삭제가능",description = "위치 정보 삭제 contentId만 있으면 삭제가능")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0 반환")
	})
	@PostMapping("/delete")
	public ResponseEntity<?> deletePlan(@org.springframework.web.bind.annotation.RequestBody Place place) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(placeService.deletePlace(place));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	//place 체크 후 있으면 update 없으면 insert
	@PostMapping("/checkInsert")
	@Operation(summary = "place 체크 후 있으면 update 없으면 insert",description = "place 체크 후 있으면 update 없으면 insert")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0 반환")
	})
	public ResponseEntity<?> checkInsertPlan(@org.springframework.web.bind.annotation.RequestBody Place place) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			
			if(null==placeService.selectPlace(place.getContentId())) {
				return ResponseEntity.ok().headers(headers).body(placeService.insertPlace(place));				
			}
			else {
				return ResponseEntity.ok().headers(headers).body(placeService.updatePlace(place));
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
	}
}
