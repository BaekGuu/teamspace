package com.ssafy.ws.model.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ws.model.Board;
import com.ssafy.ws.model.Member;
import com.ssafy.ws.model.service.BoardService;
import com.ssafy.ws.model.service.MemberService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/board")
@CrossOrigin("*")
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final MemberService memberService;

	@GetMapping(value = "/list")
	public ResponseEntity<?> selectAllBoard() {
		try {

			List<Board> list = boardService.selectAllBoard();
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

	@GetMapping(value = "/searchTitle/{title}")
	public ResponseEntity<?> selectByTitle(
			@Parameter(required = true, description = "검색할 사용자의 아이디") @PathVariable("title") String title) {
		try {

			List<Board> list = boardService.selectByTitle(title);
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

	@GetMapping(value = "/{boardId}")
	public ResponseEntity<?> searchBoardById(
			@Parameter(required = true, description = "검색할 사용자의 아이디") @PathVariable("boardId") int boardId) {
		try {

			Board board = boardService.searchBoardById(boardId);
			if (board != null) {
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
				return ResponseEntity.ok().headers(headers).body(board);
			} else {
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping(value = "/regist")
	public ResponseEntity<?> insertBoard(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Board board) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

		// 닉네임이 있는 닉네임인지 확인
		if (memberService.searchMemberById(board.getWriterId()) == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).headers(headers).build();
		}
		try {
			boardService.insertBoard(board);
			return ResponseEntity.ok().headers(headers).body(boardService.searchBoardById(board.getBoardId()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateBoard(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Board board) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

		try {
			int cnt = boardService.updateBoard(board);

			return ResponseEntity.ok().headers(headers).body(cnt);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteBoard(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Member.class))) @org.springframework.web.bind.annotation.RequestBody Board board) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
			int cnt = boardService.deleteBoard(board);
			return ResponseEntity.ok().headers(headers).body(cnt);
			
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
	}
}
