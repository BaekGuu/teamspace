package com.ssafy.ws.model.service;

import java.util.List;

import com.ssafy.ws.model.Member;

public interface MemberService {
	public List<Member> selectAllMember();
	public Member login(Member member);
	public int insertMember(Member member);
	public int updateMember(Member member);
	public int deleteMember(Member member);
	public Member searchMemberById(String id);
}
