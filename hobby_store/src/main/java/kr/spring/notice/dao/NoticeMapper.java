package kr.spring.notice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.notice.vo.NoticeFavVO;
import kr.spring.notice.vo.NoticeVO;

@Mapper
public interface NoticeMapper {
	//부모글 
	//공지사항 
	public List<NoticeVO> selectNoticeList(Map<String,Object> map);
	//공지사항 레코드 수
	public int selectNoticeCount(Map<String,Object> map);
	//공지사항 등록
	@Insert("INSERT INTO notice_board (noti_num,mem_num,status,"
			+ "noti_title,photo1,photo_name1,photo2,photo_name2,"
			+ "noti_content,noti_end) "
			+ "VALUES (notice_board_seq.nextval,#{mem_num},#{status},#{noti_title},"
			+ "#{photo1},#{photo_name1},#{photo2},#{photo_name2},#{noti_content},"
			+ "#{noti_end})")
	public void insertNotice(NoticeVO noticeVO);
	//공지사항 상세
	@Select("SELECT * FROM notice_board b JOIN member m "
			+ "USING(mem_num) JOIN member_detail d USING(mem_num) WHERE b.noti_num=#{noti_num}")
	public NoticeVO selectNotice(Integer noti_num);
	//공지사항 조회수 등록
	@Update("UPDATE notice_board SET noti_hit=noti_hit+1 WHERE noti_num=#{noti_num}")
	public void updateHit(Integer noti_num);
	//공지사항 수정
	public void updateNotice(NoticeVO noticeVO);
	//공지사항 삭제
	@Delete("DELETE FROM notice_board WHERE noti_num=#{noti_num}")
	public void deleteNotice(Integer noti_num);
	//공지사항 사진 삭제
	@Update("UPDATE notice_board SET uploadfile='',filename='' WHERE noti_num=#{noti_num}")
	public void deleteFile(Integer noti_num);

	
	//좋아요
	@Select("SELECT * FROM notice_fav WHERE "
			+ "noti_num=#{noti_num} AND mem_num=#{mem_num}")
	public NoticeFavVO selectFav(NoticeFavVO fav);
	@Select("SELECT COUNT(*) FROM notice_fav "
			+ "WHERE noti_num=#{noti_num}")
	public int selectFavCount(Integer noti_num);
	@Insert("INSERT INTO spboard_fav (fav_num,"
			+ "noti_num,mem_num) VALUES (notice_fav_seq.nextval,"
			+ "#{noti_num},#{mem_num})")
	public void insertFav(NoticeFavVO fav);
	@Delete("DELETE FROM notice_fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	@Delete("DELETE FROM notice_fav WHERE noti_num=#{noti_num}")
	public void deleteFavByNoticeNum(Integer noti_num);
}
