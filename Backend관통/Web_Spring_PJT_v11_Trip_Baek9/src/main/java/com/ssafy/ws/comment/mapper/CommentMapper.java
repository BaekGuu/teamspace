package com.ssafy.ws.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.comment.model.Comment;

@Mapper
public interface CommentMapper {
	public List<Comment> selectAllComment();
	public List<Comment> selectCommentByBoard(int boardId);
	public int insertComment(Comment comment);
	public int updateComment(Comment comment);
	public int deleteComment(Comment comment);
}
