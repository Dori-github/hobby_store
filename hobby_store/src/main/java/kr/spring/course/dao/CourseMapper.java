package kr.spring.course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import kr.spring.course.vo.CourseFavVO;
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
	@Select("SELECT COUNT(*) FROM course_reply WHERE course_num=#{course_num}")
	public int selectReplyCount(Integer course_num);
	@Select("SELECT AVG(star_auth) FROM course_star WHERE course_num=#{course_num}")
	public int selectStarAvg(Integer course_num);
	
	/*
	 * public List<CourseReplyVO> selectListReply(Map<String,Object> map);
	 * 
	 * @Select("SELECT COUNT(*) FROM spboard_reply JOIN " +
	 * "spmember USING(mem_num) " + "WHERE board_num=#{board_num}") public int
	 * selectRowCountReply( Map<String,Object> map);
	 * 
	 * @Select("SELECT * FROM spboard_reply WHERE re_num=#{re_num}") public
	 * CourseReplyVO selectReply(Integer re_num);
	 * 
	 * @Insert("INSERT INTO spboard_reply (re_num,re_content," +
	 * "re_ip,board_num,mem_num) VALUES (" +
	 * "spreply_seq.nextval,#{re_content},#{re_ip}," + "#{board_num},#{mem_num})")
	 * public void insertReply(CourseReplyVO boardReply);
	 * 
	 * @Update("UPDATE spboard_reply SET " +
	 * "re_content=#{re_content},re_ip=#{re_ip}," +
	 * "re_mdate=SYSDATE WHERE re_num=#{re_num}") public void
	 * updateReply(CourseReplyVO boardReply);
	 * 
	 * @Delete("DELETE FROM spboard_reply WHERE re_num=#{re_num}") public void
	 * deleteReply(Integer re_num);
	 * 
	 * @Delete("DELETE FROM spboard_reply WHERE board_num=#{board_num}") public void
	 * deleteReplyByBoardNum(Integer board_num);
	 */
	
	
	
	
}
