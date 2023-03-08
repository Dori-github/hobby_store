package kr.spring.mypage.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.member.vo.MemberVO;
import kr.spring.mypage.vo.MypageVO;

@Mapper
public interface MypageMapper {
	@Select("SELECT * FROM member JOIN member_detail USING(mem_num) WHERE mem_num=#{mem_num}")
	public MemberVO selectMember(Integer mem_num);
	@Update("UPDATE member SET mem_id=#{mem_id}, mem_nickname=#{mem_nickname} WHERE mem_num=#{mem_num}")
	public void updateMember(MemberVO member);
	@Update("UPDATE member_detail SET mem_name=#{mem_name},mem_cell=#{mem_cell},mem_email=#{mem_email},mem_zipcode=#{mem_zipcode},mem_address1=#{mem_address1},mem_address2=#{mem_address2},mem_mdate=SYSDATE")
	public void updateMember_detail(MemberVO member);
	@Update("UPDATE member_detail SET mem_password=#{mem_pw}")
	public void updatePassword(MemberVO member);
	@Delete("DELETE FROM member WHERE mem_num=#{mem_num}")
	public void deleteMember(Integer mem_num);
	@Delete("DELETE FROM member_detail WHERE mem_num=#{mem_num}")
	public void deleteMember_detail(Integer mem_num);
	@Update("UPDATE member_detail SET mem_photo=#{mem_photo},mem_pname=#{mem_pname} WHERE mem_num=#{mem_num}")
	public void updateProfile(MemberVO member);
}
