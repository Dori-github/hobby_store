package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.course.vo.CourseVO;
import kr.spring.event.vo.EventVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.dao.MemberMapper;
import kr.spring.member.vo.MemberVO;
import kr.spring.order.vo.OrderDetailVO;
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
		return 0;
	}

	@Override
	public List<CourseVO> selectCourseFav(Map<String, Object> map) {
		return memberMapper.selectCourseFav(map);
	}

	@Override
	public int selectItemsFavCount(int mem_num) {
		return 0;
	}

	@Override
	public List<ItemsVO> selectItemsFav(Map<String, Object> map) {
		return memberMapper.selectItemsFav(map);
	}

	@Override
	public int selectSpaceFavCount(int mem_num) {
		return 0;
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

	@Override
	public int selectCourseListCount(int mem_num) {
		return memberMapper.selectCourseListCount(mem_num);
	}

	@Override
	public List<CourseVO> selectCourseList(Map<String, Object> map) {
		return memberMapper.selectCourseList(map);
	}

	@Override
	public int selectItemsListCount(int mem_num) {
		return memberMapper.selectItemsListCount(mem_num);
	}

	@Override
	public List<ItemsVO> selectItemsList(Map<String, Object> map) {
		return memberMapper.selectItemsList(map);
	}

	@Override
	public int selectSpaceListCount(int mem_num) {
		return memberMapper.selectSpaceListCount(mem_num);
	}

	@Override
	public List<SpaceVO> selectSpaceList(Map<String, Object> map) {
		return memberMapper.selectSpaceList(map);
	}

	@Override
	public int selectOrderCountByMem_num(Map<String, Object> map) {
		return memberMapper.selectOrderCountByMem_num(map);
	}

	@Override
	public List<OrderVO> selectListOrderByMem_num(Map<String, Object> map) {
		return memberMapper.selectListOrderByMem_num(map);
	}

	@Override
	public OrderVO selectOrder(Integer order_num) {
		return memberMapper.selectOrder(order_num);
	}

	@Override
	public List<OrderDetailVO> selectListOrderDetail(Integer order_num) {
		return memberMapper.selectListOrderDetail(order_num);
	}

	@Override
	public void updateOrderCancel(Integer order_num) {
		memberMapper.updateOrderCancel(order_num);
	}

	@Override
	public void updateOrder(OrderVO order) {
		memberMapper.updateOrder(order);
	}

	@Override
	public int selectDeliveryCount(Map<String, Object> map) {
		return memberMapper.selectDeliveryCount(map);
	}

	@Override
	public List<OrderVO> selectListDelivery(Map<String, Object> map) {
		return memberMapper.selectListDelivery(map);
	}

	@Override
	public void updatePassword(MemberVO member) {
		memberMapper.updatePassword(member);
	}

	@Override
	public void deleteMember(Integer mem_num) {
		memberMapper.deleteMember(mem_num);
		memberMapper.deleteMember_detail(mem_num);
	}

	@Override
	public void updateProfile(MemberVO member) {
		memberMapper.updateProfile(member);
	}

	@Override
	public int selectEventApplyCount(Map<String, Object> map) {
		return memberMapper.selectEventApplyCount(map);
	}

	@Override
	public List<EventVO> selectListEventApply(Map<String, Object> map) {
		return memberMapper.selectListEventApply(map);
	}

	@Override
	public int selectEventCount(Map<String, Object> map) {
		return memberMapper.selectEventCount(map);
	}

	@Override
	public List<EventVO> selectListEvent(Map<String, Object> map) {
		return memberMapper.selectListEvent(map);
	}

	@Override
	public void updateEventAttr(EventVO eventVO) {
		memberMapper.updateEventAttr(eventVO);
	}

	@Override
	public int selectEventWinCount(Map<String, Object> map) {
		return memberMapper.selectEventWinCount(map);
	}

	@Override
	public List<EventVO> selectListEventWin(Map<String, Object> map) {
		return memberMapper.selectListEventWin(map);
	}

	@Override
	public void updateEventWin(EventVO eventVO) {
		memberMapper.updateEventWin(eventVO);
	}

}
