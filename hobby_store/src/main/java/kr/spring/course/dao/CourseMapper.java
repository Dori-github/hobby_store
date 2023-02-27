package kr.spring.course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.course.vo.CourseVO;

//@Mapper
public interface CourseMapper {
	//부모글
	public List<CourseVO> selectCourseList(Map<String,Object> map);
	public int selectCourseCount(Map<String,Object> map);
	@Insert("INSERT INTO course (course_num,course_name,course_onoff,course_oneweek,cate_nums,course_zipcode,course_address1,course_address2,"
			+ "course_photo1,course_photo_name1,course_photo2,course_photo_name2,course_photo3,course_photo_name3,"
			+ "course_month,course_count,course_limit,course_price,course_content) VALUES "
			+ "(course_seq.nextval,#{course_name},#{course_onoff},#{course_oneweek},#{cate_nums},#{course_zipcode},"
			+ "#{course_address1},#{course_address2},#{course_photo1},#{course_photo_name1},#{course_photo2},#{course_photo_name2},#{course_photo3},#{course_photo_name3},"
			+ "#{course_month},#{course_count},#{course_limit},${course_price},${course_content})")
	public void insertCourse(CourseVO course);
	@Insert("INSERT INTO course_time (course_num,course_reg_date,course_reg_time) VALUES "
			+ "(#{course_num},#{course_reg_date},#{course_reg_time})")
	public void insertCourse_time(CourseVO course);
	@Select("SELECT cate_num,cate_parent,cate_name FROM course_cate")
	public List<CourseVO> selectCate();
	@Select("SELECT cate_num FROM course_cate WHERE cate_name=#{cate_name}")
	public int selectCate_num(CourseVO course);
	public CourseVO selectCourse(Integer course_num);
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
	
	 
	//좋아요
	@Select("SELECT COUNT(*) FROM course_fav WHERE course_num=#{course_num}")
	public int selectFavCount(Integer course_num);
	
	
	//후기
	@Select("SELECT COUNT(*) FROM course_reply WHERE course_num=#{course_num}")
	public int selectReplyCount(Integer course_num);
	@Select("SELECT AVG(star_auth) FROM course_star WHERE course_num=#{course_num}")
	public int selectStarAvg(Integer course_num);
}
