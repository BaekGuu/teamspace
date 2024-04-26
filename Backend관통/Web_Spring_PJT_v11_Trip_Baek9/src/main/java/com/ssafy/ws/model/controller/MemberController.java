package com.ssafy.ws.model.controller;

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

import com.ssafy.member.model.MemberDto;
import com.ssafy.ws.model.Member;
import com.ssafy.ws.model.service.MemberService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/member")
@CrossOrigin("*")
@RequiredArgsConstructor
public class MemberController {
	private final MemberService memberService;

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

	@PostMapping(value = "/regist")
	public ResponseEntity<?> insertMember(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Member member) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		if(memberService.searchMemberById(member.getId())!=null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).build();
		}
		try {
			memberService.insertMember(member);
			return ResponseEntity.ok().headers(headers).body(memberService.searchMemberById(member.getId()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> searchMemberById(
			@Parameter(required = true, description = "검색할 사용자의 아이디") @PathVariable("id") String id) {
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			if(memberService.searchMemberById(id)!=null) {
				return ResponseEntity.ok().headers(headers).body(memberService.searchMemberById(id));
			}
			else {
				return ResponseEntity.notFound().headers(headers).build();
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
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
	
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateMember(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Member member) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
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
	
	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteMember(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Member member) {
		
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
