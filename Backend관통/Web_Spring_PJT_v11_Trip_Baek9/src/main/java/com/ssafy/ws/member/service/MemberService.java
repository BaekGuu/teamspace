package com.ssafy.ws.member.service;

import java.util.List;

import com.ssafy.ws.member.model.Member;

public interface MemberService {
	public List<Member> selectAllMember();
	public Member login(Member member);
	public int insertMember(Member member);
	public int updateMember(Member member);
	public int deleteMember(Member member);
	public Member searchMemberById(String id);
	public Member checkNickname(String nickName);
}
