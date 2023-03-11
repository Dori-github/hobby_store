package kr.spring.event.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.springframework.web.multipart.MultipartFile;

public class EventVO {
	private int event_num;
	@NotEmpty
	private String event_title;
	private int event_attr;
	private Date event_end;
	private int event_hit;
	@NotEmpty
	private String event_content;
	@NotEmpty
	private byte[] event_photo;
	private String event_photo_name;
	private Date event_date;
	private Date event_rdate;
	private int mem_num;
	private int course_num;
	private int items_num;
	@NotEmpty
	private String event_detail;
	private Date event_a_date;
	private int event_a_win;
	private String mem_nickname;
	
	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	public Date getEvent_a_date() {
		return event_a_date;
	}

	public void setEvent_a_date(Date event_a_date) {
		this.event_a_date = event_a_date;
	}
	public int getEvent_a_win() {
		return event_a_win;
	}

	public void setEvent_a_win(int event_a_win) {
		this.event_a_win = event_a_win;
	}

	//업로드 파일 처리
	public void setUpload(MultipartFile upload) throws IOException {
		//Multipart -> byte[] 배열로 변환
		setEvent_photo(upload.getBytes());
		//파일명
		setEvent_photo_name(upload.getOriginalFilename());
	}
	
	public void setEvent_rdate(String event_rdate) {
		if(event_rdate=="0000-00-00") {
			this.event_rdate = null;
		}
	}
	
	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public String getEvent_title() {
		return event_title;
	}
	public void setEvent_title(String event_title) {
		this.event_title = event_title;
	}
	public int getEvent_attr() {
		return event_attr;
	}
	public void setEvent_attr(int event_attr) {
		this.event_attr = event_attr;
	}
	public Date getEvent_end() {
		return event_end;
	}
	public void setEvent_end(Date event_end) {
		this.event_end = event_end;
	}
	public int getEvent_hit() {
		return event_hit;
	}
	public void setEvent_hit(int event_hit) {
		this.event_hit = event_hit;
	}
	public String getEvent_content() {
		return event_content;
	}
	public void setEvent_content(String event_content) {
		this.event_content = event_content;
	}
	public byte[] getEvent_photo() {
		return event_photo;
	}
	public void setEvent_photo(byte[] event_photo) {
		this.event_photo = event_photo;
	}
	public Date getEvent_date() {
		return event_date;
	}
	public void setEvent_date(Date event_date) {
		this.event_date = event_date;
	}
	public Date getEvent_rdate() {
		return event_rdate;
	}
	public void setEvent_rdate(Date event_rdate) {
		this.event_rdate = event_rdate;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	public int getItems_num() {
		return items_num;
	}
	public void setItems_num(int items_num) {
		this.items_num = items_num;
	}
	public String getEvent_detail() {
		return event_detail;
	}
	public void setEvent_detail(String event_detail) {
		this.event_detail = event_detail;
	}
	public String getEvent_photo_name() {
		return event_photo_name;
	}
	public void setEvent_photo_name(String event_photo_name) {
		this.event_photo_name = event_photo_name;
	}

	@Override
	public String toString() {
		return "EventVO [event_num=" + event_num + ", event_title=" + event_title + ", event_attr=" + event_attr
				+ ", event_end=" + event_end + ", event_hit=" + event_hit + ", event_content=" + event_content
				+ ", event_photo_name=" + event_photo_name + ", event_date=" + event_date + ", event_rdate="
				+ event_rdate + ", mem_num=" + mem_num + ", course_num=" + course_num + ", items_num=" + items_num
				+ ", event_detail=" + event_detail + ", event_a_date=" + event_a_date + ", event_a_win=" + event_a_win
				+ ", mem_nickname=" + mem_nickname + "]";
	}
	
}
