package kr.spring.course.service;

import java.util.Arrays;
import java.util.Iterator;
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
	public List<CourseVO> selectCourseMainList(Map<String, Object> map) {
		return courseMapper.selectCourseMainList(map);
	}
	@Override
	public List<CourseVO> selectCourseMainList2(Map<String, Object> map2) {
		return courseMapper.selectCourseMainList2(map2);
	}
	@Override
	public List<CourseVO> selectCourseMainList3(Map<String, Object> map3) {
		return courseMapper.selectCourseMainList3(map3);
	}
	@Override
	public List<CourseVO> selectCourseList(Map<String, Object> map) {
		return courseMapper.selectCourseList(map);
	}

	@Override
	public int selectCourseCount(Map<String, Object> map) {
		return courseMapper.selectCourseCount(map);
	}

	//요일,시간 course_time 테이블에 입력
	public void insertCourse_time(CourseVO course) {
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
	public void insertCourse(CourseVO course) {
		course.setCourse_num(courseMapper.selectCourse_num());
		courseMapper.insertCourse(course);
		insertCourse_time(course);
	}

	@Override
	public List<CourseTimeVO> selectCourseTime(Integer course_num) {
		return courseMapper.selectCourseTime(course_num);
	}
	
	@Override
	public CourseTimeVO selectCourseTimes(Integer course_num, String course_reg_date) {
		return courseMapper.selectCourseTimes(course_num,course_reg_date);
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
		courseMapper.deleteCourse_time(course.getCourse_num());
		courseMapper.updateCourse(course);
		insertCourse_time(course);
	}
	
	@Override
	public void deleteCourse(Integer course_num) {
		courseMapper.deleteCourse(course_num);
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
	public void deleteReplyByCourseNum(Integer course_num) {
		courseMapper.deleteReplyByCourseNum(course_num);
	}
	@Override
	public void deleteReplyWithAll(Integer reply_num) {
		courseMapper.deleteReplyFavByReplyNum(reply_num);
		courseMapper.deleteReply(reply_num);
	}
	@Override
	public Float selectStar(Integer course_num) {
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

	@Override
	public void deletePhoto2(Integer course_num) {
		courseMapper.deletePhoto2(course_num);
	}

	@Override
	public void deletePhoto3(Integer course_num) {
		courseMapper.deletePhoto2(course_num);
	}

	@Override
	public void deleteReplyPhoto1(Integer reply_num) {
		courseMapper.deleteReplyPhoto1(reply_num);
	}

	@Override
	public void deleteReplyPhoto2(Integer reply_num) {
		courseMapper.deleteReplyPhoto2(reply_num);
	}

	@Override
	public void deleteReplyPhoto3(Integer reply_num) {
		courseMapper.deleteReplyPhoto3(reply_num);
	}

	@Override
	public void deleteCourseCart(Integer course_num) {
		courseMapper.deleteCourseCart(course_num);
	}

	@Override
	public void deleteCourseWithAll(Integer course_num) {
			//course_num을 사용하여 course_num을 list 형태로 저장
			
			List<Integer> reply_nums = courseMapper.selectReplyNum(course_num);
			for(Integer num : reply_nums ) {
				//댓글 번호가 있을때 댓글 번호를 통한 댓글의 좋아요 삭제	
				courseMapper.deleteReplyFavByReplyNum(num);
			}
			//댓글 번호가 있을때 댓글 삭제 
			courseMapper.deleteReplyByCourseNum(course_num);
			//아이템의 좋아요 삭제 
			courseMapper.deleteFavByCourseNum(course_num);
			//아이템 카트 삭제
			courseMapper.deleteCourseCart(course_num);
			//요일,시간 테이블 삭제
			courseMapper.deleteCourseTime(course_num);
			//아이템 삭제
			courseMapper.deleteCourse(course_num);
		}

	@Override
	public List<Integer> selectReplyNum(Integer course_num) {
		return courseMapper.selectReplyNum(course_num);
	}
	@Override
	public List<CourseVO> selectCourseAddress(Map<String, Object> map) {
		return courseMapper.selectCourseAddress(map);
	}
	
}
	
	

