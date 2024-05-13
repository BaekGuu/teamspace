package com.ssafy.ws.comment.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ws.comment.mapper.CommentMapper;
import com.ssafy.ws.comment.model.Comment;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{

	private final CommentMapper commentMapper;
	
	@Override
	public List<Comment> selectAllComment() {
		// TODO Auto-generated method stub
		return commentMapper.selectAllComment();
	}

	@Override
	public List<Comment> selectCommentByBoard(int boardId) {
		// TODO Auto-generated method stub
		return commentMapper.selectCommentByBoard(boardId);
	}

	@Override
	public int insertComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.insertComment(comment);
	}

	@Override
	public int updateComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.updateComment(comment);
	}

	@Override
	public int deleteComment(Comment comment) {
		// TODO Auto-generated method stub
		return commentMapper.deleteComment(comment);
	}

}
