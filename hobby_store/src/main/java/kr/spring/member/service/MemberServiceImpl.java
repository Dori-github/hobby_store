package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.vo.OrderVO;
import kr.spring.space.vo.SpaceVO;
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
	public List<MemberVO> selectSearchMember(String mem_nickname) {
		return memberMapper.selectSearchMember(mem_nickname);
	}

	@Override
	public void updateMember(MemberVO member) {
		memberMapper.updateMember(member);
		memberMapper.updateMember_detail(member);
	}

	@Override
	public int selectCourseFavCount(int mem_num) {
		return memberMapper.selectCourseFavCount(mem_num);
	}

	@Override
	public List<CourseVO> selectCourseFav(Map<String, Object> map) {
		return memberMapper.selectCourseFav(map);
	}

	@Override
	public int selectItemsFavCount(int mem_num) {
		return memberMapper.selectItemsFavCount(mem_num);
	}

	@Override
	public List<ItemsVO> selectItemsFav(Map<String, Object> map) {
		return memberMapper.selectItemsFav(map);
	}

	@Override
	public int selectSpaceFavCount(int mem_num) {
		return memberMapper.selectSpaceFavCount(mem_num);
	}

	@Override
	public List<SpaceVO> selectSpaceFav(Map<String, Object> map) {
		return memberMapper.selectSpaceFav(map);
	}

	@Override
	public int selectOrderCount(int mem_num) {
		return memberMapper.selectOrderCount(mem_num);
	}

	@Override
	public List<OrderVO> selectOrderList(Map<String, Object> map) {
		return memberMapper.selectOrderList(map);
	}

	@Override
	public List<MemberVO> selectMemberList(Map<String, Object> map) {
		return memberMapper.selectMemberList(map);
	}

	@Override
	public int selectMemberRowCount(Map<String,Object> map) {
		return memberMapper.selectMemberRowCount(map);
	}

	@Override
	public void updateByAdmin(MemberVO memberVO) {
		memberMapper.updateByAdmin(memberVO);
	}


	

}
