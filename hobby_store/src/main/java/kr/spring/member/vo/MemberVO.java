package kr.spring.member.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.web.multipart.MultipartFile;

public class MemberVO {
	private int mem_num;
	//@Pattern 대문자, 소문자 사용 가능, 최대 4자부터 12자까지 설정 
	@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String mem_id;
	private String mem_nickname;
	private String mem_store;
	private int mem_auth;
	private String auto;
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
	private String country_detail;
	private int like_num;
	private String like_detail;
	private Date mem_date;
	private Date mem_mdate;
	
	//비밀번호 변경시 현재 비밀번호를 저장하는 용도로 사용
	//@Pattern(regexp="^[A-Za-z0-9]{4,12}$")
	private String now_pw;
	
	//====비밀번호 일치 여부 체크====//
	public boolean isCheckedPassword(String userPasswd) {
		if(mem_auth > 1 && mem_pw.equals(userPasswd)) {
			return true;
		}
		return false;
	}
	
	//====이미지 BLOB 처리====//
	//폼에서 파일업로드 파라미터네임은 반드시 upload로 지정할 것
	public void setUpload(MultipartFile upload)throws IOException{
		//MultipartFile -> byte[]
		setMem_photo(upload.getBytes());
		//파일 이름
		setMem_pname(upload.getOriginalFilename());
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
	public String getMem_store() {
		return mem_store;
	}
	public void setMem_store(String mem_store) {
		this.mem_store = mem_store;
	}
	public int getMem_auth() {
		return mem_auth;
	}
	public void setMem_auth(int mem_auth) {
		this.mem_auth = mem_auth;
	}
	public String getAuto() {
		return auto;
	}
	public void setAuto(String auto) {
		this.auto = auto;
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
	public String getCountry_detail() {
		return country_detail;
	}
	public void setCountry_detail(String country_detail) {
		this.country_detail = country_detail;
	}
	public int getLike_num() {
		return like_num;
	}
	public void setLike_num(int like_num) {
		this.like_num = like_num;
	}
	public String getLike_detail() {
		return like_detail;
	}
	public void setLike_detail(String like_detail) {
		this.like_detail = like_detail;
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
	public String getNow_pw() {
		return now_pw;
	}

	public void setNow_pw(String now_pw) {
		this.now_pw = now_pw;
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
