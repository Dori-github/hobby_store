package kr.spring.member.vo;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MemberVO {
	private int mem_num;
	//@Pattern 대문자, 소문자 사용 가능, 최대 4자부터 12자까지 설정 
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String mem_id;
	private String mem_nickname;
	private int mem_auth;
	private String mem_au_id;
	@NotEmpty
	private String mem_name;
	private byte[] mem_photo;
	private String mem_pname;
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String mem_pw;
	@NotEmpty
	private String mem_cell;
	@NotEmpty
	private String mem_email;
	@Size(min=5,max=5) //최소 5자 , 최대 5자 
	private String mem_zipcode;
	@NotEmpty
	private String mem_address1;
	@NotEmpty
	private String mem_address2;
	private int country_num;
	private int like_num;
	private Date mem_date;
	private Date mem_mdate;
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
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public String getMem_au_id() {
		return mem_au_id;
	}
	public void setMem_au_id(String mem_au_id) {
		this.mem_au_id = mem_au_id;
	}
	public String getMem_name() {
		return mem_name;
	}
	public void setMem_name(String mem_name) {
		this.mem_name = mem_name;
	}
	public byte[] getMem_photo() {
		return mem_photo;
	}
	public void setMem_photo(byte[] mem_photo) {
		this.mem_photo = mem_photo;
	}
	public String getMem_pname() {
		return mem_pname;
	}
	public void setMem_pname(String mem_pname) {
		this.mem_pname = mem_pname;
	}
	public String getMem_pw() {
		return mem_pw;
	}
	public void setMem_pw(String mem_pw) {
		this.mem_pw = mem_pw;
	}
	public String getMem_cell() {
		return mem_cell;
	}
	public void setMem_cell(String mem_cell) {
		this.mem_cell = mem_cell;
	}
	public String getMem_email() {
		return mem_email;
	}
	public void setMem_email(String mem_email) {
		this.mem_email = mem_email;
	}
	public String getMem_zipcode() {
		return mem_zipcode;
	}
	public void setMem_zipcode(String mem_zipcode) {
		this.mem_zipcode = mem_zipcode;
	}
	public String getMem_address1() {
		return mem_address1;
	}
	public void setMem_address1(String mem_address1) {
		this.mem_address1 = mem_address1;
	}
	public String getMem_address2() {
		return mem_address2;
	}
	public void setMem_address2(String mem_address2) {
		this.mem_address2 = mem_address2;
	}
	public int getCountry_num() {
		return country_num;
	}
	public void setCountry_num(int country_num) {
		this.country_num = country_num;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public Date getMem_date() {
		return mem_date;
	}
	public void setMem_date(Date mem_date) {
		this.mem_date = mem_date;
	}
	public Date getMem_mdate() {
		return mem_mdate;
	}
	public void setMem_mdate(Date mem_mdate) {
		this.mem_mdate = mem_mdate;
	}
	
	@Override
	public String toString() {
		return "MemberVO [mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_nickname=" + mem_nickname + ", mem_auth="
				+ mem_auth + ", mem_au_id=" + mem_au_id + ", mem_name=" + mem_name + ", mem_pname=" + mem_pname
				+ ", mem_pw=" + mem_pw + ", mem_cell=" + mem_cell + ", mem_email=" + mem_email + ", mem_zipcode="
				+ mem_zipcode + ", mem_address1=" + mem_address1 + ", mem_address2=" + mem_address2 + ", country_num="
				+ country_num + ", like_num=" + like_num + ", mem_date=" + mem_date + ", mem_mdate=" + mem_mdate + "]";
	}
	
	
	
}
