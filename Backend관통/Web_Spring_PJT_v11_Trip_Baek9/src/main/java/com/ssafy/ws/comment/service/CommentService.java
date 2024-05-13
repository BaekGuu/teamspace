package com.ssafy.ws.comment.service;

import java.util.List;

import com.ssafy.ws.comment.model.Comment;

public interface CommentService {
	public List<Comment> selectAllComment();
	public List<Comment> selectCommentByBoard(int boardId);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(Comment comment);
}
