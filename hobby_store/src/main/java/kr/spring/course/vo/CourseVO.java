package kr.spring.course.vo;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class CourseVO {
	private int course_num;
	private int mem_num;
	private String cate_nums;
	@NotEmpty
	private String course_name;
	@NotEmpty
	private String course_content;
	private byte[] course_photo1;
	private byte[] course_photo2;
	private byte[] course_photo3;
	private String course_photo_name1;
	private String course_photo_name2;
	private String course_photo_name3;
	private String course_startdate;
	private int course_month;
	private int course_count;
	private int course_price;
	@Range(min=1,max=999)
	private int course_limit;
	//@Size(min=5,max=5)
	private String course_zipcode;
	private String course_address1;
	private String course_address2;
	@NotEmpty
	private String course_onoff;
	private String course_oneweek;
	private int course_hit;
	private Date course_date;
	private Date course_mdate;

	private int cate_parent;
	@NotEmpty
	private String cate_name;
	private int cate_num;
	private String mem_nickname;
	private byte[] mem_photo;
	//별점평균
	private Float staravg;
	//후기 개수
	private int replycount;
	//좋아요 개수
	private int fav;
	//좋아요를 누른 사람의 회원번호
	private int fmem_num;
	private int fav_num;
	
	private List<CourseTimeVO> courseTimeVO;
	private String course_reg_date;
	private String course_reg_time;

	//파일 업로드 처리
	//폼에서 파일업로드 파라미터 네임은 반드시 upload1,2,3로 지정
	public void setUpload1(MultipartFile upload1)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setCourse_photo1(upload1.getBytes());
		//파일명 구하기
		setCourse_photo_name1(upload1.getOriginalFilename());
	}
	public void setUpload2(MultipartFile upload2)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setCourse_photo2(upload2.getBytes());
		//파일명 구하기
		setCourse_photo_name2(upload2.getOriginalFilename());
	}
	public void setUpload3(MultipartFile upload3)
			throws IOException{
		//MultipartFile -> byte[] 변환
		setCourse_photo3(upload3.getBytes());
		//파일명 구하기
		setCourse_photo_name3(upload3.getOriginalFilename());
	}
	
	
	public void setPrice(String price) throws Exception{
		
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
	public String getCate_nums() {
		return cate_nums;
	}
	public void setCate_nums(String cate_nums) {
		this.cate_nums = cate_nums;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public String getCourse_content() {
		return course_content;
	}
	public void setCourse_content(String course_content) {
		this.course_content = course_content;
	}
	public byte[] getCourse_photo1() {
		return course_photo1;
	}
	public void setCourse_photo1(byte[] course_photo1) {
		this.course_photo1 = course_photo1;
	}
	public byte[] getCourse_photo2() {
		return course_photo2;
	}
	public void setCourse_photo2(byte[] course_photo2) {
		this.course_photo2 = course_photo2;
	}
	public byte[] getCourse_photo3() {
		return course_photo3;
	}
	public void setCourse_photo3(byte[] course_photo3) {
		this.course_photo3 = course_photo3;
	}
	public String getCourse_photo_name1() {
		return course_photo_name1;
	}
	public void setCourse_photo_name1(String course_photo_name1) {
		this.course_photo_name1 = course_photo_name1;
	}
	public String getCourse_photo_name2() {
		return course_photo_name2;
	}
	public void setCourse_photo_name2(String course_photo_name2) {
		this.course_photo_name2 = course_photo_name2;
	}
	public String getCourse_photo_name3() {
		return course_photo_name3;
	}
	public void setCourse_photo_name3(String course_photo_name3) {
		this.course_photo_name3 = course_photo_name3;
	}
	public int getCourse_price() {
		return course_price;
	}
	public void setCourse_price(int course_price) {
		this.course_price = course_price;
	}
	public int getCourse_limit() {
		return course_limit;
	}
	public void setCourse_limit(int course_limit) {
		this.course_limit = course_limit;
	}
	public String getCourse_zipcode() {
		return course_zipcode;
	}
	public void setCourse_zipcode(String course_zipcode) {
		this.course_zipcode = course_zipcode;
	}
	public String getCourse_address1() {
		return course_address1;
	}
	public void setCourse_address1(String course_address1) {
		this.course_address1 = course_address1;
	}
	public String getCourse_address2() {
		return course_address2;
	}
	public void setCourse_address2(String course_address2) {
		this.course_address2 = course_address2;
	}
	public String getCourse_onoff() {
		return course_onoff;
	}
	public void setCourse_onoff(String course_onoff) {
		this.course_onoff = course_onoff;
	}
	public String getCourse_oneweek() {
		return course_oneweek;
	}
	public void setCourse_oneweek(String course_oneweek) {
		this.course_oneweek = course_oneweek;
	}
	public int getCourse_hit() {
		return course_hit;
	}
	public void setCourse_hit(int course_hit) {
		this.course_hit = course_hit;
	}
	public Date getCourse_date() {
		return course_date;
	}
	public void setCourse_date(Date course_date) {
		this.course_date = course_date;
	}
	public Date getCourse_mdate() {
		return course_mdate;
	}
	public void setCourse_mdate(Date course_mdate) {
		this.course_mdate = course_mdate;
	}
	public int getCate_parent() {
		return cate_parent;
	}
	public void setCate_parent(int cate_parent) {
		this.cate_parent = cate_parent;
	}
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	public int getCate_num() {
		return cate_num;
	}
	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	
	public Float getStaravg() {
		return staravg;
	}
	public void setStaravg(Float staravg) {
		this.staravg = staravg;
	}
	public int getReplycount() {
		return replycount;
	}
	public void setReplycount(int replycount) {
		this.replycount = replycount;
	}
	public int getFav() {
		return fav;
	}
	public void setFav(int fav) {
		this.fav = fav;
	}
	public int getCourse_month() {
		return course_month;
	}
	public void setCourse_month(int course_month) {
		this.course_month = course_month;
	}
	public int getCourse_count() {
		return course_count;
	}
	public void setCourse_count(int course_count) {
		this.course_count = course_count;
	}
	public String getCourse_reg_date() {
		return course_reg_date;
	}
	public void setCourse_reg_date(String course_reg_date) {
		this.course_reg_date = course_reg_date;
	}
	public String getCourse_reg_time() {
		return course_reg_time;
	}
	public void setCourse_reg_time(String course_reg_time) {
		this.course_reg_time = course_reg_time;
	}
	public List<CourseTimeVO> getCourseTimeVO() {
		return courseTimeVO;
	}
	public void setCourseTimeVO(List<CourseTimeVO> courseTimeVO) {
		this.courseTimeVO = courseTimeVO;
	}
	public int getFmem_num() {
		return fmem_num;
	}
	public void setFmem_num(int fmem_num) {
		this.fmem_num = fmem_num;
	}
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}
	
	public String getCourse_startdate() {
		return course_startdate;
	}
	public void setCourse_startdate(String course_startdate) {
		this.course_startdate = course_startdate;
	}
	@Override
	public String toString() {
		return "CourseVO [course_num=" + course_num + ", mem_num=" + mem_num + ", cate_nums=" + cate_nums
				+ ", course_name=" + course_name + ", course_content=" + course_content + ", course_photo_name1="
				+ course_photo_name1 + ", course_photo_name2=" + course_photo_name2 + ", course_photo_name3="
				+ course_photo_name3 + ", course_startdate=" + course_startdate + ", course_month=" + course_month
				+ ", course_count=" + course_count + ", course_price=" + course_price + ", course_limit=" + course_limit
				+ ", course_zipcode=" + course_zipcode + ", course_address1=" + course_address1 + ", course_address2="
				+ course_address2 + ", course_onoff=" + course_onoff + ", course_oneweek=" + course_oneweek
				+ ", course_hit=" + course_hit + ", course_date=" + course_date + ", course_mdate=" + course_mdate
				+ ", cate_parent=" + cate_parent + ", cate_name=" + cate_name + ", cate_num=" + cate_num
				+ ", mem_nickname=" + mem_nickname + ", staravg=" + staravg + ", replycount=" + replycount + ", fav="
				+ fav + ", fmem_num=" + fmem_num + ", fav_num=" + fav_num + ", courseTimeVO=" + courseTimeVO
				+ ", course_reg_date=" + course_reg_date + ", course_reg_time=" + course_reg_time + "]";
	}
	
}