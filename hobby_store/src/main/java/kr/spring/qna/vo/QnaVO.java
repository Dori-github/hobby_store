package kr.spring.qna.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;

public class QnaVO {
	private int qna_num;
	@NotEmpty
	private String qna_title;
	@NotEmpty
	private String qna_content;
	private int qna_hit;
	private Date qna_date;
	private Date qna_mdate;
	
	private int mem_num;
	private int items_num;
	private int course_num;
	private int space_num;
	
	private String id;
	private String nick_name;
	
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public int getQna_hit() {
		return qna_hit;
	}
	public void setQna_hit(int qna_hit) {
		this.qna_hit = qna_hit;
	}
	public Date getQna_date() {
		return qna_date;
	}
	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}
	public Date getQna_mdate() {
		return qna_mdate;
	}
	public void setQna_mdate(Date qna_mdate) {
		this.qna_mdate = qna_mdate;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getItems_num() {
		return items_num;
	}
	public void setItems_num(int items_num) {
		this.items_num = items_num;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	public int getSpace_num() {
		return space_num;
	}
	public void setSpace_num(int space_num) {
		this.space_num = space_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	@Override
	public String toString() {
		return "QnaVO [qna_num=" + qna_num + ", qna_title=" + qna_title + ", qna_content=" + qna_content + ", qna_hit="
				+ qna_hit + ", qna_date=" + qna_date + ", qna_mdate=" + qna_mdate + ", mem_num=" + mem_num
				+ ", items_num=" + items_num + ", course_num=" + course_num + ", space_num=" + space_num + ", id=" + id
				+ ", nick_name=" + nick_name + "]";
	}
}
