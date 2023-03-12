package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.qna.dao.QnaMapper;
import kr.spring.qna.vo.QnaReplyVO;
import kr.spring.qna.vo.QnaVO;

@Service
@Transactional
public class QnaServiceImpl implements QnaService{
	
	@Autowired
	private QnaMapper qnaMapper;
	
	@Override
	public List<QnaVO> selectList(Map<String, Object> map) {
		return qnaMapper.selectList(map);
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		return qnaMapper.selectRowCount(map);
	}

	@Override
	public void insertQna(QnaVO qna) {
		qnaMapper.insertQna(qna);
	}

	@Override
	public QnaVO selectQna(Integer qna_num) {
		return qnaMapper.selectQna(qna_num);
	}

	@Override
	public void updateHit(Integer qna_num) {
		qnaMapper.updateHit(qna_num);
		
	}

	@Override
	public void updateQna(QnaVO qna) {
		qnaMapper.updateQna(qna);
	}

	@Override
	public void deleteQna(Integer qna_num) {
		//댓글 삭제 
		qnaMapper.deleteReply(qna_num);
		//부모글 삭제 
		qnaMapper.deleteQna(qna_num);
	}
	
	//파일 넣는거 없어서 사용 안
	@Override
	public void deleteFile(Integer qna_num) {
		// TODO Auto-generated method stub
		
	}
	
	//QnA 댓글 
	@Override
	public List<QnaReplyVO> selectListReply(Map<String, Object> map) {
		return (List<QnaReplyVO>) qnaMapper.selectListReply(map);
		//return qnaMapper.selectListReply(map);
	}

	@Override
	public int selectRowCountReply(Map<String, Object> map) {
		return qnaMapper.selectRowCountReply(map);
	}

	@Override
	public QnaReplyVO selectReply(Integer re_num) {
		return qnaMapper.selectReply(re_num);
	}

	@Override
	public void insertReply(QnaReplyVO qnaReply) {
		qnaMapper.insertReply(qnaReply);
	}

	@Override
	public void updateReply(QnaReplyVO qnaReply) {
		qnaMapper.updateReply(qnaReply);
	}

	@Override
	public void deleteReply(Integer re_num) {
		qnaMapper.deleteReply(re_num);
	}

}
