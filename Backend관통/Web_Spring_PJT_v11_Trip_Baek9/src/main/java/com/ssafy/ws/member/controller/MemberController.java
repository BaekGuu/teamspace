package com.ssafy.ws.member.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.member.model.Member;
import com.ssafy.ws.member.service.MemberService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "member 컨트롤러",description = "멤버의 CRUD를 담당하는 컨트롤러")
public class MemberController {
	private final MemberService memberService;

	
	@Operation(summary = "멤버 목록",description = "전체 멤버 탐색 : id,nickName,email 반환 ")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "전체 멤버 반환 성공")
	})
	@GetMapping(value = "/list")
	public ResponseEntity<?> selectAllMember() {
		try {
			List<Member> list = memberService.selectAllMember();
			if (list != null && !list.isEmpty()) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
				return ResponseEntity.ok().headers(headers).body(list);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@Operation(summary = "멤버 등록",description = "필요 데이터 : id,nickName,password,email")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공, id,nickName,email 반환"),
			@ApiResponse(responseCode = "409",description = "아이디나 닉네임 중복")
	})
	@PostMapping(value = "/regist")
	public ResponseEntity<?> insertMember(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Member member) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		//닉네임, 아이디 중복 체크
		if(memberService.searchMemberById(member.getId())!=null || memberService.checkNickname(member.getNickName())!=null ) {
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).build();
		}
		try {
			memberService.insertMember(member);
			return ResponseEntity.ok().headers(headers).body(memberService.searchMemberById(member.getId()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@Operation(summary = "멤버 id 기반 탐색",description = "필요 데이터 : id")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공, id,nickName,email 반환"),
			@ApiResponse(responseCode = "404",description = "해당 아이디 없음")
	})
	@GetMapping(value = "/id/{id}")
	public ResponseEntity<?> searchMemberById(
			@Parameter(required = true, description = "검색할 사용자의 아이디") @PathVariable("id") String id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			if(memberService.searchMemberById(id)!=null) {
				return ResponseEntity.ok().headers(headers).body(memberService.searchMemberById(id));
			}
			else {
				return ResponseEntity.ok().headers(headers).body(0);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@Operation(summary = "닉네임 중복 체크",description = "필요 데이터 : nickname, 닉네임이 있으면 1, 없으면 0 반환")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "데이터 있던 없던 200 반환. 있으면 1, 없으면 0")
	})
	@GetMapping(value = "/nickName/{nickName}")
	public ResponseEntity<?> checkNickname(
			@Parameter(required = true, description = "검색할 사용자의 닉네임") @PathVariable("nickName") String nickName) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			if(memberService.checkNickname(nickName)!=null) {
				return ResponseEntity.ok().headers(headers).body(1);
			}
			else {
				return ResponseEntity.ok().headers(headers).body(0);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	
	
	@Operation(summary = "로그인",description = "필요 데이터 : id,password")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공, id,password,nickName,email 반환"),
			@ApiResponse(responseCode = "404",description = "해당 정보 없음")
	})
	@PostMapping(value = "/login")
	public ResponseEntity<?> login(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Member member) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
			Member result = memberService.login(member);
			if(result!=null) {
				return ResponseEntity.ok().headers(headers).body(result);
			}
			else {
				return ResponseEntity.notFound().headers(headers).build();
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@Operation(summary = "회원정보 수정",description = "필요 데이터 : 기존 id 바꿀 nickName,password,email. 아이디 변경 불가")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공 1 반환, 닉네임 중복 0 반환"),
			@ApiResponse(responseCode = "500",description = "에러")
	})
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateMember(
			@RequestBody(description = "변경할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Member member) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
			
			if(memberService.checkNickname(member.getNickName())!=null) {//닉네임 중복 체크
				return ResponseEntity.ok().headers(headers).body(0);
			}
			
			int cnt = memberService.updateMember(member);
			if(cnt==1) {
				return ResponseEntity.ok().headers(headers).body(1);
			}
			else {
				return ResponseEntity.ok().headers(headers).body(0);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@Operation(summary = "회원 삭제",description = "필요 데이터 : id,password")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공 1 반환")
	})
	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteMember(
			@RequestBody(description = "삭제할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Member member) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
			int cnt = memberService.deleteMember(member);
			if(cnt==1) {
				return ResponseEntity.ok().headers(headers).body(1);
			}
			else {
				return ResponseEntity.ok().headers(headers).body(0);
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
