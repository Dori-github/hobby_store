package kr.spring.course.vo;

import java.sql.Date;

public class CourseVO {
	private int course_num;
	private int mem_num;
	private int cate_num;
	private String course_name;
	private String course_content;
	private String photo1;
	private String photo2;
	private String photo3;
	private int price;
	private int limit ;
	private String zipcode;
	private String address1;
	private String address2;
	private String onoff;
	private String oneweek;
	private int hit;
	private Date date;
	private Date mdate;
	
	private String id;
	private String nick_name;
	private double star;
	private int reply;
	private int fav;
	private String course_reg_date;
	private String course_reg_time;
	
	
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
	public int getCate_num() {
		return cate_num;
	}
	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
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
	public String getPhoto1() {
		return photo1;
	}
	public void setPhoto1(String photo1) {
		this.photo1 = photo1;
	}
	public String getPhoto2() {
		return photo2;
	}
	public void setPhoto2(String photo2) {
		this.photo2 = photo2;
	}
	public String getPhoto3() {
		return photo3;
	}
	public void setPhoto3(String photo3) {
		this.photo3 = photo3;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getLimit() {
		return limit;
	}
	public void setLimit(int limit) {
		this.limit = limit;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getAddress1() {
		return address1;
	}
	public void setAddress1(String address1) {
		this.address1 = address1;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public String getOnoff() {
		return onoff;
	}
	public void setOnoff(String onoff) {
		this.onoff = onoff;
	}
	public String getOneweek() {
		return oneweek;
	}
	public void setOneweek(String oneweek) {
		this.oneweek = oneweek;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getMdate() {
		return mdate;
	}
	public void setMdate(Date mdate) {
		this.mdate = mdate;
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
	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	public int getReply() {
		return reply;
	}
	public void setReply(int reply) {
		this.reply = reply;
	}
	public int getFav() {
		return fav;
	}
	public void setFav(int fav) {
		this.fav = fav;
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
	@Override
	public String toString() {
		return "CourseVO [course_num=" + course_num + ", mem_num=" + mem_num + ", cate_num=" + cate_num
				+ ", course_name=" + course_name + ", course_content=" + course_content + ", photo1=" + photo1
				+ ", photo2=" + photo2 + ", photo3=" + photo3 + ", price=" + price + ", limit=" + limit + ", zipcode="
				+ zipcode + ", address1=" + address1 + ", address2=" + address2 + ", onoff=" + onoff + ", oneweek="
				+ oneweek + ", hit=" + hit + ", date=" + date + ", mdate=" + mdate + ", id=" + id + ", nick_name="
				+ nick_name + ", star=" + star + ", reply=" + reply + ", fav=" + fav + ", course_reg_date="
				+ course_reg_date + ", course_reg_time=" + course_reg_time + "]";
	}
}
