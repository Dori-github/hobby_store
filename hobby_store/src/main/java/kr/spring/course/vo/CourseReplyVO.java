package kr.spring.course.vo;

import java.io.IOException;
import java.util.Arrays;

import org.springframework.web.multipart.MultipartFile;

import kr.spring.util.DurationFromNow;

public class CourseReplyVO { 
	private int reply_num;
	private String reply_content;
	private byte[] reply_photo1;
	private byte[] reply_photo2;
	private byte[] reply_photo3;
	private String reply_photo_name1;
	private String reply_photo_name2;
	private String reply_photo_name3;
	private String reply_date;
	private String reply_mdate;
	private int course_num;
	private int mem_num;
	
	private String mem_id;
	private String mem_nickname;
	private byte[] mem_photo;
	
	private int fav_cnt;
	private int star;
	private float star_avg;
	private float star5_per;
	
	
	//파일 업로드 처리
		//폼에서 파일업로드 파라미터 네임은 반드시 upload1,2,3로 지정
		public void setUpload1(MultipartFile upload1)
				throws IOException{
			//MultipartFile -> byte[] 변환
			setReply_photo1(upload1.getBytes());
			//파일명 구하기
			setReply_photo_name1(upload1.getOriginalFilename());
		}
		public void setUpload2(MultipartFile upload2)
				throws IOException{
			//MultipartFile -> byte[] 변환
			setReply_photo2(upload2.getBytes());
			//파일명 구하기
			setReply_photo_name2(upload2.getOriginalFilename());
		}
		public void setUpload3(MultipartFile upload3)
				throws IOException{
			//MultipartFile -> byte[] 변환
			setReply_photo3(upload3.getBytes());
			//파일명 구하기
			setReply_photo_name3(upload3.getOriginalFilename());
		}
		
	
	
	
	public int getReply_num() {
		return reply_num;
	}
	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public String getReply_photo_name1() {
		return reply_photo_name1;
	}
	public void setReply_photo_name1(String reply_photo_name1) {
		this.reply_photo_name1 = reply_photo_name1;
	}
	public String getReply_photo_name2() {
		return reply_photo_name2;
	}
	public void setReply_photo_name2(String reply_photo_name2) {
		this.reply_photo_name2 = reply_photo_name2;
	}
	public String getReply_photo_name3() {
		return reply_photo_name3;
	}
	public void setReply_photo_name3(String reply_photo_name3) {
		this.reply_photo_name3 = reply_photo_name3;
	}
	public String getReply_date() {
		return reply_date;
	}
	public void setReply_date(String reply_date) {
		this.reply_date = DurationFromNow.getTimeDiffLabel(reply_date);
	}
	public String getReply_mdate() {
		return reply_mdate;
	}
	public void setReply_mdate(String reply_mdate) {
		this.reply_mdate = DurationFromNow.getTimeDiffLabel(reply_mdate);
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
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	public int getFav_cnt() {
		return fav_cnt;
	}
	public void setFav_cnt(int fav_cnt) {
		this.fav_cnt = fav_cnt;
	}
	public int getStar() {
		return star;
	}
	public void setStar(int star) {
		this.star = star;
	}
	public float getStar_avg() {
		return star_avg;
	}
	public void setStar_avg(float star_avg) {
		this.star_avg = star_avg;
	}
	public float getStar5_per() {
		return star5_per;
	}
	public void setStar5_per(float star5_per) {
		this.star5_per = star5_per;
	}
	public byte[] getReply_photo1() {
		return reply_photo1;
	}
	public void setReply_photo1(byte[] reply_photo1) {
		this.reply_photo1 = reply_photo1;
	}
	public byte[] getReply_photo2() {
		return reply_photo2;
	}
	public void setReply_photo2(byte[] reply_photo2) {
		this.reply_photo2 = reply_photo2;
	}
	public byte[] getReply_photo3() {
		return reply_photo3;
	}
	public void setReply_photo3(byte[] reply_photo3) {
		this.reply_photo3 = reply_photo3;
	}
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	@Override
	public String toString() {
		return "CourseReplyVO [reply_num=" + reply_num + ", reply_content=" + reply_content + ", reply_photo1="
				+ Arrays.toString(reply_photo1) + ", reply_photo2=" + Arrays.toString(reply_photo2) + ", reply_photo3="
				+ Arrays.toString(reply_photo3) + ", reply_photo_name1=" + reply_photo_name1 + ", reply_photo_name2="
				+ reply_photo_name2 + ", reply_photo_name3=" + reply_photo_name3 + ", reply_date=" + reply_date
				+ ", reply_mdate=" + reply_mdate + ", course_num=" + course_num + ", mem_num=" + mem_num + ", mem_id="
				+ mem_id + ", mem_nickname=" + mem_nickname + ", mem_photo=" + Arrays.toString(mem_photo) + ", fav_cnt="
				+ fav_cnt + ", star=" + star + ", star_avg=" + star_avg + ", star5_per=" + star5_per + "]";
	}
}
