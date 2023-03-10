package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.qna.dao.QnaMapper;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Integer qna_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateQna(QnaVO qna) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteQna(Integer qna_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteFile(Integer qna_num) {
		// TODO Auto-generated method stub
		
	}

}
