package com.ssafy.ws.board.service;

import java.util.List;

import com.ssafy.ws.board.model.Board;
import com.ssafy.ws.member.model.Member;

public interface BoardService {
	public List<Board> selectAllBoard();
	public List<Board> selectByTitle(String title);
	public Board searchBoardById(int boardId);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(Board board);
}
