package kr.spring.course.vo;

import java.util.List;

public class CourseTimeVO {  
	private int course_num;
	private int mem_num;  
	private String course_reg_date;
	private List<String> course_reg_times;
	private String course_reg_time;

	public String getCourse_reg_date() {
		return course_reg_date;
	}
	public void setCourse_reg_date(String course_reg_date) {
		this.course_reg_date = course_reg_date;
	}
	public List<String> getCourse_reg_times() {
		return course_reg_times;
	}
	public void setCourse_reg_times(List<String> course_reg_times) {
		this.course_reg_times = course_reg_times;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	public String getCourse_reg_time() {
		return course_reg_time;
	}
	public void setCourse_reg_time(String course_reg_time) {
		this.course_reg_time = course_reg_time;
	}
	@Override
	public String toString() {
		return "CourseTimeVO [course_num=" + course_num + ", mem_num=" + mem_num + ", course_reg_date="
				+ course_reg_date + ", course_reg_times=" + course_reg_times + "]";
	}
	
	
}
