package kr.spring.mypage.service;

import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.vo.MypageVO;

public interface MypageService {
	public MemberVO selectMember(Integer mem_num);
	public void updateMember(MemberVO member);
	public void updateMember_detail(MemberVO member);
	public void updatePassword(MemberVO member);
	public void deleteMember(Integer mem_num);
	public void deleteMember_detail(Integer mem_num);
	public void updateProfile(MemberVO member);
}
