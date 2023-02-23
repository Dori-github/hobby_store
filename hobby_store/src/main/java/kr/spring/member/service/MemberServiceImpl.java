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


	

}
