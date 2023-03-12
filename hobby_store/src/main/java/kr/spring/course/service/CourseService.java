package kr.spring.course.service;

import java.util.List;
import java.util.Map;

import kr.spring.course.vo.CourseReplyFavVO;
import kr.spring.course.vo.CourseFavVO;
import kr.spring.course.vo.CourseReplyVO;
import kr.spring.course.vo.CourseVO;

public interface CourseService { 
	//부모글
	public List<CourseVO> selectCourseList(Map<String,Object> map);
	public int selectCourseCount(Map<String,Object> map);
	public void insertCourse(CourseVO course);
	public List<CourseVO> selectCate();
	public int selectCate_num(String cate_name);
	public CourseVO selectCourse(Integer course_num);
	public void updateHit(Integer course_num);
	public void updateCourse(CourseVO course);
	public void deleteCourse(Integer course_num);
	public void deletePhoto(Integer course_num);
	
	
	//좋아요
	public List<CourseVO> selectFavCheck();
	public CourseFavVO selectFav(CourseFavVO fav);
	public int selectFavCount(Integer course_num);
	public void insertFav(CourseFavVO fav);
	public void deleteFav(Integer fav_num);
	public void deleteFavByCourseNum(Integer course_num);


	//후기
	public List<CourseReplyVO> selectListReply(Map<String,Object> map);
	public int selectReplyCount(Map<String,Object> map);
	public CourseReplyVO selectReply(Integer reply_num);
	public void insertReply(CourseReplyVO courseReply);
	public void updateReply(CourseReplyVO boardReply);
	public void deleteReply(Integer reply_num);
	public void deleteReplyByBoardNum(Integer board_num);
	public float selectStar(Integer course_num);
	public int select5star(Integer course_num);
	public int selectallstar(Integer course_num);
	public void deleteReplyPhoto(Integer reply_num,Integer photo_type);
	
	//후기 좋아요
	public CourseReplyFavVO selectReplyFavCheck();
	public CourseReplyFavVO selectReplyFav(CourseReplyFavVO fav);
	public int selectReplyFavCount(Integer reply_num);
	public void insertReplyFav(CourseReplyFavVO fav);
	public void deleteReplyFav(Integer fav_num);
	public void deleteReplyFavByReplyNum(Integer reply_num);


}
