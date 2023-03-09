package kr.spring.course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.course.vo.CourseFavVO;
import kr.spring.course.vo.CourseReplyFavVO;
import kr.spring.course.vo.CourseReplyVO;
import kr.spring.course.vo.CourseTimeVO;
import kr.spring.course.vo.CourseVO;

@Mapper
public interface CourseMapper {
	//부모글
	public List<CourseVO> selectCourseList(Map<String,Object> map);
	@Select("SELECT course_seq.nextval FROM dual")
	public int selectCourse_num();
	public int selectCourseCount(Map<String,Object> map);
	@Insert("INSERT INTO course (course_num,mem_num,course_name,course_onoff,course_oneweek,cate_nums,course_zipcode,course_address1,course_address2,"
			+ "course_photo1,course_photo_name1,course_photo2,course_photo_name2,course_photo3,course_photo_name3,"
			+ "course_month,course_count,course_limit,course_price,course_content) VALUES "
			+ "(#{course_num},#{mem_num},#{course_name},#{course_onoff},#{course_oneweek, jdbcType=VARCHAR},#{cate_nums},#{course_zipcode},"
			+ "#{course_address1},#{course_address2},#{course_photo1},#{course_photo_name1},#{course_photo2},#{course_photo_name2},#{course_photo3},#{course_photo_name3},"
			+ "#{course_month},#{course_count},#{course_limit},#{course_price},#{course_content})")
	public void insertCourse(CourseVO course);
	
	@Insert("INSERT INTO course_time (ctime_num,course_num,mem_num,course_reg_date,course_reg_time) VALUES "
			+ "(course_time_seq.nextval,#{course_num},#{mem_num},#{course_reg_date},#{course_reg_time})")
	public void insertCourse_time(CourseTimeVO vo);
	@Select("SELECT * FROM course_cate")
	public List<CourseVO> selectCate();
	@Select("SELECT cate_num FROM course_cate WHERE cate_name=#{cate_name}")
	public int selectCate_num(CourseVO course);
	@Select("SELECT * FROM course JOIN member USING (mem_num) WHERE course_num = #{course_num}")
	public CourseVO selectCourse(Integer course_num);
	@Update("UPDATE course SET course_hit=course_hit+1 WHERE course_num=#{course_num}")
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
	
	 
	//좋아요
	@Select("SELECT * FROM course FULL OUTER JOIN course_fav USING(course_num)")
	public List<CourseVO> selectFavCheck();
	@Select("SELECT * FROM course_fav WHERE course_num=#{course_num} AND fmem_num=#{fmem_num}")
	public CourseFavVO selectFav(CourseFavVO fav);
	@Select("SELECT COUNT(*) FROM course_fav WHERE course_num=#{course_num}")
	public int selectFavCount(Integer course_num);
	@Insert("INSERT INTO course_fav (fav_num,course_num,fmem_num) VALUES (course_fav_seq.nextval,#{course_num},#{fmem_num})")
	public void insertFav(CourseFavVO fav);
	@Delete("DELETE FROM course_fav WHERE fav_num=#{fav_num}")
	public void deleteFav(Integer fav_num);
	@Delete("DELETE FROM course_fav WHERE course_num=#{course_num}")
	public void deleteFavByCourseNum(Integer course_num);
	
	
	//후기
	public List<CourseReplyVO> selectListReply(Map<String,Object> map);
	public int selectReplyCount(Map<String,Object> map);
	@Select("SELECT * FROM course_reply WHERE reply_num=#{reply_num}")
	public CourseReplyVO selectReply(Integer reply_num);
	@Insert("INSERT INTO course_reply (reply_num,star_auth,reply_content,reply_photo1,reply_photo2,reply_photo3,reply_photo_name1,reply_photo_name2,reply_photo_name3,course_num,mem_num) "
			+ "VALUES (course_reply_seq.nextval,#{star_auth},#{reply_content},#{reply_photo1,jdbcType=BLOB},#{reply_photo2,jdbcType=BLOB},#{reply_photo3,jdbcType=BLOB},"
			+ "#{reply_photo_name1,jdbcType=VARCHAR},#{reply_photo_name2,jdbcType=VARCHAR},#{reply_photo_name3,jdbcType=VARCHAR},#{course_num},#{mem_num})")
	public void insertReply(CourseReplyVO courseReply);
	@Update("UPDATE course_reply SET reply_content=#{reply_content},reply_photo1=#{reply_photo1},reply_photo2=#{reply_photo2},"
			+ "reply_photo3=#{reply_photo3},reply_photo_name1=#{reply_photo_name1},reply_photo_name2=#{reply_photo_name2},"
			+ "reply_photo_name3=#{reply_photo_name3},reply_mdate=SYSDATE WHERE reply_num=#{reply_num}")
	public void updateReply(CourseReplyVO courseReply);
	@Delete("DELETE FROM course_reply WHERE reply_num=#{reply_num}")
	public void deleteReply(Integer reply_num);
	@Delete("DELETE FROM course_reply WHERE course_num=#{course_num}")
	public void deleteReplyByCourseNum(Integer course_num);
	//후기 별점평균
	@Select("SELECT ROUND(AVG(star_auth),1) AS starcount  FROM course_reply WHERE course_num = #{course_num} GROUP BY course_num")
	public Float selectStar(Integer course_num);
	@Select("SELECT COUNT(reply_num) AS star5 FROM course_reply WHERE star_auth = 5 AND course_num = #{course_num}")
	public int select5star(Integer course_num);
	@Select("SELECT COUNT(reply_num) AS starall FROM course_reply WHERE course_num = #{course_num}")
	public int selectallstar(Integer course_num);
	
	
	//후기 좋아요
	@Select("SELECT * FROM course_reply_fav WHERE reply_num=#{reply_num} AND fmem_num=#{fmem_num}")
	public CourseReplyFavVO selectReplyFav(CourseReplyFavVO fav);
	@Select("SELECT COUNT(*) FROM course_reply_fav WHERE reply_num=#{reply_num}")
	public int selectReplyFavCount(Integer reply_num);
	@Insert("INSERT INTO course_reply_fav (fav_num,reply_num,fmem_num) VALUES (course_reply_fav_seq.nextval,#{reply_num},#{fmem_num})")
	public void insertReplyFav(CourseReplyFavVO fav);
	@Delete("DELETE FROM course_reply_fav WHERE fav_num=#{fav_num}")
	public void deleteReplyFav(Integer fav_num);
	@Delete("DELETE FROM course_reply_fav WHERE reply_num=#{reply_num}")
	public void deleteReplyFavByReplyNum(Integer reply_num);

}
