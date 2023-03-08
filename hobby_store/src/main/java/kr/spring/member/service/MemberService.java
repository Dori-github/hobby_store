package kr.spring.member.service;

import java.util.List;
import java.util.Map;

import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.space.vo.SpaceVO;
public interface MemberService {
	public void insertMember(MemberVO member);
	public MemberVO selectCheckMember(String mem_id);
	public MemberVO selectCheckNickname(String mem_nickname);
	public List<MemberVO> getCountryList();
	public List<MemberVO> getLikeList();
	public String selectIdSearch(MemberVO vo);
	public void updateMemberPasswd(MemberVO member);

	//자동로그인
	public void updateAu_id(String mem_au_id,String mem_id);
	public MemberVO selectAu_id(String mem_au_id);
	public void deleteAu_id(int mem_num);

	//채팅 회원이름 검색
	public List<MemberVO> selectSearchMember(String mem_id);

	public String getMem_name(int mem_num);
	
	
	
	
	
	//======마이페이지======//
	//회원번호 상세
	public MemberVO selectMember(Integer mem_num);
	
	//회원정보 업데이트
	public void updateMember(MemberVO member);
	
	//강의 좋아요 리스트
	public int selectCourseFavCount(int mem_num);
	public List<CourseVO> selectCourseFav(Map<String,Object> map);
	//상품 좋아요 리스트
	public int selectItemsFavCount(int mem_num);
	public List<ItemsVO> selectItemsFav(Map<String,Object> map);
	//공간대여 좋아요 리스트
	public int selectSpaceFavCount(int mem_num);
	public List<SpaceVO> selectSpaceFav(Map<String,Object> map);
}
