package kr.spring.course.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import kr.spring.course.vo.CourseVO;

@Mapper
public interface CourseMapper {
	//부모글
	public List<CourseVO> selectList(Map<String,Object> map);
	public int selectRowCount(Map<String,Object> map);
	@Insert("INSERT INTO course (course_num,course_name,onoff,cate_num,zipcode,address1,address2,"
			+ "photo1,photo2,photo3,course_reg_date,course_reg_time,limit,price,course_content) VALUES "
			+ "(course_seq.nextval,#{course_name},#{onoff},#{cate_num},#{zipcode},#{address1},#{address2},"
			+ "#{photo1},#{photo2},#{photo3},#{course_reg_date},#{course_reg_time},#{limit},${price},${course_content})")
	public void insertCourse(CourseVO course);
	public CourseVO selectCourse(Integer course_num);
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
	
	 
	//좋아요
	
	
	
	//후기
	
	
}
