package com.ssafy.ws.member.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.ws.member.model.Member;

@Mapper
public interface MemberMapper {
	public List<Member> selectAllMember();
	public Member login(Member member);
	public int insertMember(Member member);
	public int updateMember(Member member);
	public int deleteMember(Member member);
	public Member searchMemberById(String id);
	public Member searchMemberByNickname(String id);
	public Member checkNickname(String nickName);
}
