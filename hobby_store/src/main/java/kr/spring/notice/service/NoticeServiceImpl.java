package kr.spring.notice.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.notice.dao.NoticeMapper;
import kr.spring.notice.vo.NoticeFavVO;
import kr.spring.notice.vo.NoticeVO;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService{

	@Autowired
	private NoticeMapper noticeMapper;
	
	@Override
	public List<NoticeVO> selectNoticeList(Map<String, Object> map) {
		return noticeMapper.selectNoticeList(map);
	}

	@Override
	public int selectNoticeCount(Map<String, Object> map) {
		return noticeMapper.selectNoticeCount(map);
	}

	@Override
	public void insertNotice(NoticeVO noticeVO) {
		noticeMapper.insertNotice(noticeVO);
	}

	@Override
	public NoticeVO selectNotice(Integer noti_num) {
		return noticeMapper.selectNotice(noti_num);
	}

	@Override
	public void updateHit(Integer noti_num) {
		noticeMapper.updateHit(noti_num);
	}

	@Override
	public void updateNotice(NoticeVO noticeVO) {
		noticeMapper.updateNotice(noticeVO);
	}

	@Override
	public void deleteNotice(Integer noti_num) {
		//좋아요 삭제 
		noticeMapper.deleteFavByNoticeNum(noti_num);
		
		//부모글 삭제 
		noticeMapper.deleteNotice(noti_num);
	}

	@Override
	public void deleteFile(Map<String, Integer> map) {
		noticeMapper.deleteFile(map);
		
	}
	
	//공지사항 좋아요
	@Override
	public NoticeFavVO selectFav(NoticeFavVO fav) {
		return noticeMapper.selectFav(fav);
	}

	@Override
	public int selectFavCount(Integer noti_num) {
		return noticeMapper.selectFavCount(noti_num);
	}

	@Override
	public void insertFav(NoticeFavVO fav) {
		noticeMapper.insertFav(fav);
	}

	@Override
	public void deleteFav(Integer fav_num) {
		noticeMapper.deleteFav(fav_num);
	}

	
	
	 
	
	
	
	

	

}
