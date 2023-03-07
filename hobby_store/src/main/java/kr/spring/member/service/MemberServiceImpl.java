package kr.spring.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;

@Service
@Transactional //트랜잭션 처리 저장 
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberMapper memberMapper;

	@Override
	public void insertMember(MemberVO member) {
		//mem_num을 구한 다음에(시퀀스 전달)
				member.setMem_num(memberMapper.selectMem_num());
				//insertMember에 자바빈을 넣어줌 (시퀀스가 들어가있는 자바빈 전달)
				memberMapper.insertMember(member);
				//insertMember_detail에도 자바빈을 넣어줌
				memberMapper.insertMember_detail(member);	
	}

	@Override
	public MemberVO selectCheckMember(String mem_id) {
		return memberMapper.selectCheckMember(mem_id);
	}

	@Override
	public MemberVO selectCheckNickname(String mem_nickname) {
		return memberMapper.selectCheckNickname(mem_nickname);
	}

	@Override
	public List<MemberVO> getCountryList() {
		return memberMapper.getCountryList();
	}

	@Override
	public List<MemberVO> getLikeList() {
		return memberMapper.getLikeList();
	}

	@Override
	public void updateAu_id(String mem_au_id, String mem_id) {
		memberMapper.updateAu_id(mem_au_id, mem_id);
		
	}

	@Override
	public MemberVO selectAu_id(String mem_au_id) {
		return memberMapper.selectAu_id(mem_au_id);
	}

	@Override
	public void deleteAu_id(int mem_num) {
		memberMapper.deleteAu_id(mem_num);
		
	}

	@Override
	public String selectIdSearch(MemberVO vo) {
		return memberMapper.selectIdSearch(vo);
	}

	@Override
	public void updateMemberPasswd(MemberVO member) {
		memberMapper.updateMemberPasswd(member);
		
	}

	@Override
	public String getMem_name(int mem_num) {
		return memberMapper.getMem_name(mem_num);
	}

	@Override
	public MemberVO selectMember(Integer mem_num) {
		return memberMapper.selectMember(mem_num);
	}

	@Override
	public List<MemberVO> selectSearchMember(String mem_id) {
		return memberMapper.selectSearchMember(mem_id);
	}


	

}
