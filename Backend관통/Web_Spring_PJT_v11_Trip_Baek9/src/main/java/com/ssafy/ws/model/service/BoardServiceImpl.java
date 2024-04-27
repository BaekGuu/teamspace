package com.ssafy.ws.model.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ssafy.ws.model.Board;
import com.ssafy.ws.model.mapper.BoardMapper;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

	private final BoardMapper boardMapper;

	@Override
	public List<Board> selectAllBoard() {
		// TODO Auto-generated method stub
		return boardMapper.selectAllBoard();
	}

	@Override
	public List<Board> selectByTitle(String title) {
		return boardMapper.selectByTitle(title);
	}

	@Override
	public Board searchBoardById(int boardId) {
		return boardMapper.searchBoardById(boardId);
	}

	@Override
	public int insertBoard(Board board) {
		// TODO Auto-generated method stub
		return boardMapper.insertBoard(board);
	}

	@Override
	public int updateBoard(Board board) {
		// TODO Auto-generated method stub
		return boardMapper.updateBoard(board);
	}

	@Override
	public int deleteBoard(Board board) {
		// TODO Auto-generated method stub
		return boardMapper.deleteBoard(board);
	}

}
