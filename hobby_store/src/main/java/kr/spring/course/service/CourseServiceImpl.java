package kr.spring.course.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.spring.course.dao.CourseMapper;
import kr.spring.course.vo.CourseFavVO;
import kr.spring.course.vo.CourseReplyFavVO;
import kr.spring.course.vo.CourseReplyVO;
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
		courseMapper.updateHit(course_num);
	}

	@Override
	public void updateCourse(CourseVO course) {
		courseMapper.updateCourse(course);
	}
	
	@Override
	public void deleteCourse(Integer course_num) {
		courseMapper.deleteCourse(course_num);
	}

	@Override
	public void deletePhoto(Integer course_num) {
		courseMapper.deletePhoto(course_num);
	}

	@Override
	public List<CourseVO> selectCate() {
		return courseMapper.selectCate();
	}

	@Override
	public int selectCate_num(String cate_name) {
		return courseMapper.selectCate_num(cate_name);
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
	public List<CourseReplyVO> selectListReply(Map<String, Object> map) {
		return courseMapper.selectListReply(map);
	}
	@Override
	public int selectReplyCount(Map<String, Object> map) {
		return courseMapper.selectReplyCount(map);
	}
	@Override
	public CourseReplyVO selectReply(Integer reply_num) {
		return courseMapper.selectReply(reply_num);
	}
	@Override
	public void insertReply(CourseReplyVO courseReply) {
		courseMapper.insertReply(courseReply);
	}
	@Override
	public void updateReply(CourseReplyVO courseReply) {
		courseMapper.updateReply(courseReply);
	}
	@Override
	public void deleteReply(Integer re_num) {
		courseMapper.deleteReply(re_num);
	}
	@Override
	public void deleteReplyByBoardNum(Integer course_num) {
		courseMapper.deleteReplyByCourseNum(course_num);
	}
	@Override
	public float selectStar(Integer course_num) {
		return courseMapper.selectStar(course_num);
	}
	@Override
	public int select5star(Integer course_num) {
		return courseMapper.select5star(course_num);
	}

	@Override
	public int selectallstar(Integer course_num) {
		return courseMapper.selectallstar(course_num);
	}
	@Override
	public void deleteReplyPhoto(Integer reply_num, Integer photo_type) {
		courseMapper.deleteReplyPhoto(reply_num, photo_type);
	}
	
	
	
	
	//후기 좋아요
	@Override
	public CourseReplyFavVO selectReplyFav(CourseReplyFavVO fav) {
		return courseMapper.selectReplyFav(fav);
	}

	@Override
	public int selectReplyFavCount(Integer reply_num) {
		return courseMapper.selectReplyFavCount(reply_num);
	}

	@Override
	public void insertReplyFav(CourseReplyFavVO fav) {
		courseMapper.insertReplyFav(fav);
	}

	@Override
	public void deleteReplyFav(Integer fav_num) {
		courseMapper.deleteReplyFav(fav_num);
	}

	@Override
	public void deleteReplyFavByReplyNum(Integer reply_num) {
		courseMapper.deleteReplyFavByReplyNum(reply_num);
	}

	@Override
	public CourseReplyFavVO selectReplyFavCheck() {
		return courseMapper.selectReplyFavCheck();
	}

	
	
}
