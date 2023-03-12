package kr.spring.qna.service;

import java.util.List;
import java.util.Map;

import kr.spring.qna.vo.QnaVO;

public interface QnaService {
	//부모
	public List<QnaVO> selectList(Map<String,Object> map);

	public int selectRowCount(Map<String,Object> map);

	public void insertQna(QnaVO qna);

	public QnaVO selectQna(Integer qna_num);

	public void updateHit(Integer qna_num);

	public void updateQna(QnaVO qna);

	public void deleteQna(Integer qna_num);

	public void deleteFile(Integer qna_num);

	//댓글
	
}
