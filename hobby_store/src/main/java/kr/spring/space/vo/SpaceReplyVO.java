package kr.spring.space.vo;

import java.sql.Date;

public class SpaceReplyVO {
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
	private int space_num;
	private int mem_num;
	
	private String mem_id;
	private String mem_nickname;
	private byte[] mem_photo;
	
	private int fav_cnt;
	private int star;
	private float star_avg;
	private float star5_per;
	
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
		this.reply_date = reply_date;
	}
	public String getReply_mdate() {
		return reply_mdate;
	}
	public void setReply_mdate(String reply_mdate) {
		this.reply_mdate = reply_mdate;
	}
	public int getSpace_num() {
		return space_num;
	}
	public void setSpace_num(int space_num) {
		this.space_num = space_num;
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
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
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
	
	

}
