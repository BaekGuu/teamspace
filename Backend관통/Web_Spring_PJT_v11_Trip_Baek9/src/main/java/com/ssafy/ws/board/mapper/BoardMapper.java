package com.ssafy.ws.board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.board.model.Board;

@Mapper
public interface BoardMapper {
	public List<Board> selectAllBoard();
	public List<Board> selectByTitle(String title);
	public Board searchBoardById(int boardId);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(Board board);
}
