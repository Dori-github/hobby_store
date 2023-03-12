package kr.spring.qna.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.qna.vo.QnaReplyVO;
import kr.spring.qna.vo.QnaVO;


@Mapper
public interface QnaMapper {
	public List<QnaVO> selectList(Map<String,Object> map);
	
	public int selectRowCount(Map<String,Object> map);
	public void insertQna(QnaVO qna);
	@Select("SELECT * FROM qna b JOIN member m "
			+ "USING(mem_num) JOIN member_detail d USING(mem_num) "
			+ "WHERE b.qna_num=#{qna_num}")
	public QnaVO selectQna(Integer qna_num);
	@Update("UPDATE qna SET qna_hit=qna_hit+1 WHERE qna_num=#{qna_num}")
	public void updateHit(Integer qna_num);
	public void updateQna(QnaVO qna);
	@Delete("DELETE FROM qna WHERE qna_num=#{qna_num}")
	public void deleteQna(Integer qna_num);
	//deleteFile은 생략함 
	public void deleteFile(Integer qna_num);
	
	//QnA 댓글
	public List<QnaReplyVO> selectListReply(Map<String,Object>map);
	@Select("SELECT COUNT(*) FROM qna_reply JOIN "
			  + "member USING(mem_num) "
			  + "WHERE qna_num=#{qna_num}")
	public int selectRowCountReply(Map<String,Object> map);
	@Select("SELECT * FROM qna_reply WHERE re_num=#{re_num}")
	public QnaReplyVO selectReply(Integer re_num);
	@Insert("INSERT INTO qna_reply (re_num,re_content,"
			  + "qna_num,mem_num) VALUES ("
			  + "qna_reply_seq.nextval,#{re_content},"
			  + "#{qna_num},#{mem_num})")
	public void insertReply(QnaReplyVO boardReply);
	@Update("UPDATE qna_reply SET "
			  + "re_content=#{re_content},"
			  + "re_mdate=SYSDATE WHERE re_num=#{re_num}")
	public void updateReply(QnaReplyVO boardReply);
	@Delete("DELETE FROM qna_reply WHERE re_num=#{re_num}")
	public void deleteReply(Integer re_num);
	@Delete("DELETE FROM qna_reply WHERE qna_num=#{qna_num}")
	public void deleteReplyByBoardNum(Integer board_num);
}
