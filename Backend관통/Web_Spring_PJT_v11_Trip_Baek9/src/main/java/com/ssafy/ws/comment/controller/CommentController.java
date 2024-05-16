package com.ssafy.ws.comment.controller;

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

import com.ssafy.ws.comment.model.Comment;
import com.ssafy.ws.comment.service.CommentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/comment")
//@CrossOrigin("*")
@RequiredArgsConstructor
@Tag(name = "Comment 컨트롤러",description = "댓글의 CRUD를 담당하는 컨트롤러")
public class CommentController {
	private final CommentService commentService;

	
	@Operation(summary = "댓글 전체 목록",description = "전체 댓글 탐색")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "전체 댓글 목록 반환")
	})
	@GetMapping("")
	public ResponseEntity<?> selectAllComment() {

		try {
			List<Comment> list = commentService.selectAllComment();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}

	
	@Operation(summary = "특정 Board의 댓글 검색",description = "BoardId에 해당하는 댓글 반환. 없으면 null")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "BoardId에 해당하는 댓글 반환")
	})
	@GetMapping(value = "/{boardId}")
	public ResponseEntity<?> selectCommentByBoard(@PathVariable("boardId") int boardId) {
		try {
			List<Comment> list = commentService.selectCommentByBoard(boardId);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@Operation(summary = "댓글 등록",description = "필요 데이터 : comment, memberId,boardId")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "성공시 1, 실패시 0 반환"),
			@ApiResponse(responseCode = "500",description = "에러")
	})
	@PostMapping("/regist")
	public ResponseEntity<?> insertComment(@org.springframework.web.bind.annotation.RequestBody Comment comment) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
			if(1==commentService.insertComment(comment)) {
				return ResponseEntity.ok().headers(headers).body(1);				
			}
			else {
				return ResponseEntity.ok().headers(headers).body(0);
			}
		} catch (Exception e) {
			return exceptionHandling(e);
		}

	}
	
	@Operation(summary = "댓글 수정",description = "필요 데이터 : memberId,boardId,commentId 변경한 comment")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "수정 성공 1, 실패시 0"),
			@ApiResponse(responseCode = "500",description = "에러")
	})
	@PutMapping(value = "/update")
	public ResponseEntity<?> updateComment(@org.springframework.web.bind.annotation.RequestBody Comment comment) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
			if(1==commentService.updateComment(comment)) {				
				return ResponseEntity.ok().headers(headers).body(1);
			}
			else {				
				return ResponseEntity.ok().headers(headers).body(0);
			}
		}catch(Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@Operation(summary = "댓글 삭제",description = "필요 데이터 :commenId,memberId")
	@ApiResponses(value= {
			@ApiResponse(responseCode = "200",description = "삭제 성공시 1, 실패시 0"),
			@ApiResponse(responseCode = "500",description = "에러")
	})
	@PostMapping(value = "/delete")
	public ResponseEntity<?> deleteComment(@org.springframework.web.bind.annotation.RequestBody Comment comment){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
		
		try {
			if(1==commentService.deleteComment(comment)) {				
				return ResponseEntity.ok().headers(headers).body(1);
			}
			else {				
				return ResponseEntity.ok().headers(headers).body(0);
			}
		}catch(Exception e) {
			return exceptionHandling(e);
		}
	}

	private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
	}
}
