package kr.spring.member.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.course.vo.CourseVO;
import kr.spring.items.vo.ItemsVO;
import kr.spring.member.vo.MemberVO;
import kr.spring.space.vo.SpaceVO;

@Mapper
public interface MemberMapper {
	//회원관리 - 일반회원
	@Select("SELECT member_seq.nextval FROM dual")
	public int selectMem_num();
	@Insert("INSERT INTO member (mem_num,mem_id,mem_nickname) VALUES (#{mem_num},#{mem_id},#{mem_nickname})")
	public void insertMember(MemberVO member);
	@Insert("INSERT INTO member_detail (mem_num,"
			  + "mem_name,mem_pw,mem_cell,mem_email,mem_zipcode,mem_address1,"
			  + "mem_address2,country_num,like_num) VALUES (#{mem_num},"
			  + "#{mem_name},#{mem_pw},#{mem_cell},#{mem_email},"
			  + "#{mem_zipcode},#{mem_address1},#{mem_address2},#{country_num},#{like_num})")
	public void insertMember_detail(MemberVO member);
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,m.mem_au_id,"
			  + "d.mem_pw,m.mem_nickname,d.mem_email,d.mem_cell FROM "
			  + "member m LEFT OUTER JOIN member_detail d "
			  + "ON m.mem_num=d.mem_num WHERE m.mem_id=#{mem_id}")
	public MemberVO selectCheckMember(String mem_id);
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,m.mem_au_id,"
			  + "d.mem_pw,m.mem_nickname,d.mem_email FROM "
			  + "member m LEFT OUTER JOIN member_detail d "
			  + "ON m.mem_num=d.mem_num WHERE m.mem_nickname=#{mem_nickname}")
	public MemberVO selectCheckNickname(String mem_nickname);
  
	//선호지역
	@Select("SELECT * FROM member_country")
	public List<MemberVO> getCountryList();
		
	//주요관심사
	@Select("SELECT * FROM member_like")
	public List<MemberVO> getLikeList();
	
	//자동로그인
	@Update("UPDATE member SET mem_au_id=#{mem_au_id} WHERE mem_id=#{mem_id}")
	public void updateAu_id(@Param("mem_au_id") String mem_au_id, @Param("mem_id") String mem_id);
	@Select("SELECT m.mem_num,m.mem_id,m.mem_auth,m.mem_au_id,"
		  + "d.mem_pw,m.mem_nickname,d.mem_email FROM "
		  + "member m JOIN member_detail d ON "
		  + "m.mem_num=d.mem_num WHERE m.mem_au_id=#{mem_au_id}")
	public MemberVO selectAu_id(String mem_au_id);
	@Update("UPDATE member SET mem_au_id='' WHERE mem_num=#{mem_num}")
	public void deleteAu_id(int mem_num);
	
	//아이디찾기
	@Select("SELECT mem_id FROM member m LEFT OUTER JOIN "
			+ "member_detail d ON m.mem_num=d.mem_num "
			+ "WHERE d.mem_email=#{mem_email} AND d.mem_cell=#{mem_cell}")
	public String selectIdSearch(MemberVO vo);	
	
	//비밀번호 변경 
	@Update("UPDATE member_detail SET mem_pw = #{mem_pw} WHERE mem_num=#{mem_num}")
	public void updateMemberPasswd(MemberVO member); // 마이페이지 - 회원 비밀번호 변경
	

	//채팅 회원이름 검색
	@Select("SELECT mem_num,mem_id,mem_nickname FROM member "
			+ "WHERE mem_auth >= 2 AND mem_id LIKE '%' || #{mem_id} || '%'")
	public List<MemberVO> selectSearchMember(String mem_id);

	@Select("SELECT mem_name FROM member WHERE mem_num=#{mem_num}")
	public String getMem_name(int mem_num);
	
	
	
	
	
	
	//======마이페이지======//
	//회원 상세
	@Select("SELECT * FROM member JOIN member_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	//회원정보 업데이트
	@Update("UPDATE member SET mem_nickname=#{mem_nickname} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	@Update("UPDATE member_detail SET mem_name=#{mem_name},mem_cell=#{mem_cell},mem_email=#{mem_email},mem_zipcode=#{mem_zipcode},mem_address1=#{mem_address1},mem_address2=#{mem_address2},mem_mdate=SYSDATE WHERE mem_num=#{mem_num}")
	public void updateMember_detail(MemberVO member);
	//강사 좋아요 리스트
	@Select("SELECT COUNT(*) FROM course_fav f JOIN member m ON f.fmem_num=m.mem_num WHERE f.fmem_num=#{mem_num}")
	public int selectCourseFavCount(int mem_num);
	public List<CourseVO> selectCourseFav(Map<String,Object> map);
	//상품 좋아요 리스트
	@Select("SELECT COUNT(*) FROM items_fav f JOIN member m ON f.fmem_num=m.mem_num WHERE f.fmem_num=#{mem_num}")
	public int selectItemsFavCount(int mem_num);
	public List<ItemsVO> selectItemsFav(Map<String,Object> map);
	//공간대여 좋아요 리스트
	@Select("SELECT COUNT(*) FROM space_fav f JOIN member m ON f.fmem_num=m.mem_num WHERE f.fmem_num=#{mem_num}")
	public int selectSpaceFavCount(int mem_num);
	public List<SpaceVO> selectSpaceFav(Map<String,Object> map);
		
}
