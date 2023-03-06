package kr.spring.course.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.course.dao.CourseMapper;
import kr.spring.course.vo.CourseFavVO;
import kr.spring.course.vo.CourseTimeVO;
import kr.spring.course.vo.CourseVO; 

@Service
@Transactional
public class CourseServiceImpl implements CourseService{

	@Autowired
	private CourseMapper courseMapper;
	
	//==========부모글==========//
	@Override
	public List<CourseVO> selectCourseList(Map<String, Object> map) {
		return courseMapper.selectCourseList(map);
	}

	@Override
	public int selectCourseCount(Map<String, Object> map) {
		return courseMapper.selectCourseCount(map);
	}

	@Override
	public void insertCourse(CourseVO course) {
		course.setCourse_num(courseMapper.selectCourse_num());
		courseMapper.insertCourse(course);
		for(CourseTimeVO vo : course.getCourseTimeVO()) {
			if(vo.getCourse_reg_date()!=null) {
				vo.setCourse_num(course.getCourse_num());
				vo.setMem_num(course.getMem_num());
				String output = "";
				List<String> time = vo.getCourse_reg_times();
				//공백 또는 null 제거
				time.removeAll(Arrays.asList("",null));
				for(int i=0;i<vo.getCourse_reg_times().size();i++) {
					output += vo.getCourse_reg_times().get(i);
					if(i<vo.getCourse_reg_times().size()-1) output += ",";
				}
				System.out.println("~~~~~~~~~~~~~~~"+output);
				vo.setCourse_reg_time(output);
				courseMapper.insertCourse_time(vo);
			}
		}
	}

	@Override
	public CourseVO selectCourse(Integer course_num) {
		return courseMapper.selectCourse(course_num);
	}

	@Override
	public void updateHit(Integer course_num) {
		
	}

	@Override
	public void updateCourse(CourseVO course) {
		
	}
	
	@Override
	public void deleteCourse(Integer course_num) {
		
	}

	@Override
	public void deletePhoto(Integer course_num) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<CourseVO> selectCate() {
		return courseMapper.selectCate();
	}

	@Override
	public int selectCate_num(CourseVO course) {
		return courseMapper.selectCate_num(course);
	}

	
	
	
	//==========좋아요============//
	@Override
	public List<CourseVO> selectFavCheck() {
		return courseMapper.selectFavCheck();
	}
	@Override
	public int selectFavCount(Integer course_num) {
		return courseMapper.selectFavCount(course_num);
	}
	@Override
	public CourseFavVO selectFav(CourseFavVO fav) {
		return courseMapper.selectFav(fav);
	}
	@Override
	public void insertFav(CourseFavVO fav) {
		courseMapper.insertFav(fav);
	}

	@Override
	public void deleteFav(Integer fav_num) {
		courseMapper.deleteFav(fav_num);
	}

	@Override
	public void deleteFavByCourseNum(Integer course_num) {
		courseMapper.deleteFavByCourseNum(course_num);
	}

	
	
	
	
	
	
	//후기
	@Override
	public int selectReplyCount(Integer course_num) {
		return courseMapper.selectReplyCount(course_num);
	}

	@Override
	public int selectStarAvg(Integer course_num) {
		return courseMapper.selectStarAvg(course_num);
	}

	

	
	
}
