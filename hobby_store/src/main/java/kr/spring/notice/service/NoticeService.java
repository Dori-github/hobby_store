package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import kr.spring.notice.vo.NoticeFavVO;
import kr.spring.notice.vo.NoticeVO;

public interface NoticeService {
	//부모글 
	//공지사항 
	public List<NoticeVO> selectNoticeList(Map<String,Object> map);
	//공지사항 레코드 수
	public int selectNoticeCount(Map<String,Object> map);
	//공지사항 등록
	public void insertNotice(NoticeVO noticeVO);
	//공지사항 상세
	public NoticeVO selectNotice(Integer noti_num);
	//공지사항 조회수 등록
	public void updateHit(Integer noti_num);
	//공지사항 수정
	public void updateNotice(NoticeVO noticeVO);
	//공지사항 삭제
	public void deleteNotice(Integer noti_num);
	//공지사항 사진 삭제
	public void deleteFile(Map<String,Integer> map);


	//좋아요
	public NoticeFavVO selectFav(NoticeFavVO fav);
	public int selectFavCount(Integer noti_num);
	public void insertFav(NoticeFavVO fav);
	public void deleteFav(Integer fav_num);
}
