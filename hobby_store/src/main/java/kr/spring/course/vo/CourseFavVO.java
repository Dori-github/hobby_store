package kr.spring.course.vo;

public class CourseFavVO {
	private int fav_num;
	private int course_num;
	private int fmem_num;
	
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	public int getFmem_num() {
		return fmem_num;
	}
	public void setFmem_num(int fmem_num) {
		this.fmem_num = fmem_num;
	}
	@Override
	public String toString() {
		return "CourseFavVO [fav_num=" + fav_num + ", course_num=" + course_num + ", fmem_num=" + fmem_num + "]";
	}
}