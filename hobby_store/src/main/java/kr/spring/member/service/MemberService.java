package kr.spring.member.service;

import java.util.List;

import kr.spring.member.vo.MemberVO;

public interface MemberService {
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String mem_id);
	public MemberVO selectCheckNickname(String mem_nickname);
	public List<MemberVO> getCountryList();
	public List<MemberVO> getLikeList();
	public String selectIdSearch(MemberVO vo);

	//자동로그인
	public void updateAu_id(String mem_au_id,String mem_id);
	public MemberVO selectAu_id(String mem_au_id);
	public void deleteAu_id(int mem_num);



}
