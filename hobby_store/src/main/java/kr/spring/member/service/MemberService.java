package kr.spring.member.service;

import java.util.List;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String mem_id);
	public MemberVO selectCheckNickname(String mem_nickname);
	public List<MemberVO> getCountryList();
	public List<MemberVO> getLikeList();


}
