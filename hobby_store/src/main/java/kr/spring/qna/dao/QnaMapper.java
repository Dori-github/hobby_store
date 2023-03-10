package kr.spring.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.spring.qna.vo.QnaVO;


@Mapper
public interface QnaMapper {
	public List<QnaVO> selectList(Map<String,Object> map);
	
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO qna (items_num,space_num,course_num,qna_num,qna_title,"
			  + "qna_content,mem_num) "
			  + "VALUES (#{items_num},#{space_num},#{course_num},qna_seq.nextval,#{qna_title},"
			  + "#{qna_content},#{mem_num})")
	public QnaVO insertQna(QnaVO qna);
	
	public QnaVO selectQna(Integer qna_num);
	
	public void updateHit(Integer qna_num);
	
	public void updateQna(QnaVO qna);
	
	public void deleteQna(Integer qna_num);
	
	public void deleteFile(Integer qna_num);
}
