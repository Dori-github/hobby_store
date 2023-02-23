package kr.spring.course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import kr.spring.course.vo.CourseVO;

@Mapper
public interface CourseMapper {
	//부모글
	public List<CourseVO> selectCourseList(Map<String,Object> map);
	public int selectCourseCount(Map<String,Object> map);
	@Insert("INSERT INTO course (course_num,course_name,course_onoff,cate_parent,cate_name,course_zipcode,course_address1,course_address2,"
			+ "course_photo1,course_photo2,course_photo3,course_reg_date,course_reg_time,course_limit,course_price,course_content) VALUES "
			+ "(course_seq.nextval,#{course_name},#{course_onoff},#{cate_parent},#{cate_name},#{course_zipcode},"
			+ "#{course_address1},#{course_address2},#{course_photo1},#{course_photo2},#{course_photo3},"
			+ "#{course_reg_date},#{course_reg_time},#{course_limit},${course_price},${course_content})")
	public void insertCourse(CourseVO course);
	@Select("SELECT cate_parent,cate_name FROM course_cate")
	public List<CourseVO> selectCate();
	public CourseVO selectCourse(Integer course_num);
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
	
	 
	//좋아요
	
	
	
	//후기
	
	
}
