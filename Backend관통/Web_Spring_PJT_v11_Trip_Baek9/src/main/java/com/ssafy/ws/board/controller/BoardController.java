package com.ssafy.ws.board.controller;

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

import com.ssafy.ws.board.model.Board;
import com.ssafy.ws.board.service.BoardService;
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
@RequestMapping("/board")
@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "board 컨트롤러",description = "자유게시판의 CRUD를 담당하는 컨트롤러")
public class BoardController {
	private final BoardService boardService;
	private final MemberService memberService;

	
	@Operation(summary = "글 목록",description = "전체 글 탐색 : boardId, writerId, title, writingTime 반환 ")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "전체 글 목록 반환")
	})
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
	
	
	@Operation(summary = "글 검색",description = "해당 단어를 제목에 포함하는 글의 boardId,title,writerId,writingTime 출력. 단어가 null이면 전체 출력 ")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "조건에 해당하는 글 목록 반환")
	})
	@GetMapping(value = "/searchTitle/{title}")
	public ResponseEntity<?> selectByTitle(
			@Parameter(required = true, description = "해당 단어를 제목에 포함하는 글 리스트 반환") @PathVariable("title") String title) {
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

	@Operation(summary = "보드 id로 검색",description = "보드 id를 이용한 상세 페이지 검색")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "board 상세 정보 반환"),
			@ApiResponse(responseCode = "404",description = "실패시 404 not found")
	})
	@GetMapping(value = "/{boardId}")
	public ResponseEntity<?> searchBoardById(
			@Parameter(required = true, description = "board의 id를 기반으로 글의 상세 정보 반환") @PathVariable("boardId") int boardId) {
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

	@Operation(summary = "글 등록",description = "필요 데이터 : writer_id, title, content")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "board 상세 정보 반환"),
			@ApiResponse(responseCode = "409",description = "FK 오류")
	})
	@PostMapping(value = "/regist")
	public ResponseEntity<?> insertBoard(
			@RequestBody(description = "등록할 새 글 정보.", required = true,  content = @Content(schema = @Schema(implementation = Board.class))) @org.springframework.web.bind.annotation.RequestBody Board board
			) {

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

	@Operation(summary = "글 수정",description = "필요 데이터 : writerId,boardId, 변경한 title, 변경한 content")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "수정 성공")
	})
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateBoard(
			@RequestBody(description = "변경할 글 정보.", required = true, content = @Content(schema = @Schema(implementation = Board.class))) @org.springframework.web.bind.annotation.RequestBody Board board) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));

		try {
			int cnt = boardService.updateBoard(board);

			return ResponseEntity.ok().headers(headers).body(cnt);

		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@Operation(summary = "글 삭제",description = "필요 데이터 : writerId, boardId")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "글 삭제 성공")
	})
	
	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteBoard(
			@RequestBody(description = "등록할 회원정보.", required = true, content = @Content(schema = @Schema(implementation = Board.class))) @org.springframework.web.bind.annotation.RequestBody Board board) {
		
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
