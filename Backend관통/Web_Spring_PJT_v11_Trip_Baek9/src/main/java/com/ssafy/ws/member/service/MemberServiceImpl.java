package com.ssafy.ws.member.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.ws.member.mapper.MemberMapper;
import com.ssafy.ws.member.model.Member;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
	
	private final MemberMapper memberMapper;
	
	@Override
	public List<Member> selectAllMember() {
		// TODO Auto-generated method stub
		return memberMapper.selectAllMember();
	}

	@Override
	public Member login(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.login(member);
	}

	@Override
	public int insertMember(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.insertMember(member);
	}

	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.updateMember(member);
	}

	@Override
	public int deleteMember(Member member) {
		// TODO Auto-generated method stub
		return memberMapper.deleteMember(member);
	}

	@Override
	public Member searchMemberById(String id) {
		// TODO Auto-generated method stub
		return memberMapper.searchMemberById(id);
	}

	@Override
	public Member checkNickname(String nickName) {
		return memberMapper.checkNickname(nickName);
	}


}
