package kr.spring.course.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.course.dao.CourseMapper;
import kr.spring.course.vo.CourseVO; 

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseMapper courseMapper;
	
	@Override
	public List<CourseVO> selectList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int selectRowCount(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void insertCourse(CourseVO course) {
		courseMapper.insertCourse(course);
		
	}

	@Override
	public CourseVO selectCourse(Integer course_num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateHit(Integer course_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCourse(CourseVO course) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCourse(Integer course_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePhoto(Integer course_num) {
		// TODO Auto-generated method stub
		
	}

}
