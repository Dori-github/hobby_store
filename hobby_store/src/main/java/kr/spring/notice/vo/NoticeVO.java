package kr.spring.notice.vo;

import java.io.IOException;
import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

public class NoticeVO {
	private int noti_num;
	private int status;
	private String noti_end;
	private Date noti_date;
	private Date noti_mdate;
	private String noti_title;
	private String noti_content;
	private byte[] photo1;
	private String photo_name1;
	private byte[] photo2;
	private String photo_name2;
	private int noti_hit;
	private int mem_num;
	private String mem_nickname;
	private String mem_id;
	private int fav_cnt;

	//업로드 파일 처리
	public void setUpload1(MultipartFile upload1) 
			throws IOException {
		//MultipartFile -> byte[] 변환
		setPhoto1(upload1.getBytes());
		//파일명 구하기
		setPhoto_name1(upload1.getOriginalFilename());
	}

	public void setUpload2(MultipartFile upload2) 
			throws IOException {
		//MultipartFile -> byte[] 변환
		setPhoto2(upload2.getBytes());
		//파일명 구하기
		setPhoto_name2(upload2.getOriginalFilename());
	}

	public int getNoti_num() {
		return noti_num;
	}

	public void setNoti_num(int noti_num) {
		this.noti_num = noti_num;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getNoti_end() {
		return noti_end;
	}

	public void setNoti_end(String noti_end) {
		this.noti_end = noti_end;
	}

	public Date getNoti_date() {
		return noti_date;
	}

	public void setNoti_date(Date noti_date) {
		this.noti_date = noti_date;
	}

	public Date getNoti_mdate() {
		return noti_mdate;
	}

	public void setNoti_mdate(Date noti_mdate) {
		this.noti_mdate = noti_mdate;
	}

	public String getNoti_title() {
		return noti_title;
	}

	public void setNoti_title(String noti_title) {
		this.noti_title = noti_title;
	}

	public String getNoti_content() {
		return noti_content;
	}

	public void setNoti_content(String noti_content) {
		this.noti_content = noti_content;
	}

	public byte[] getPhoto1() {
		return photo1;
	}

	public void setPhoto1(byte[] photo1) {
		this.photo1 = photo1;
	}

	public String getPhoto_name1() {
		return photo_name1;
	}

	public void setPhoto_name1(String photo_name1) {
		this.photo_name1 = photo_name1;
	}

	public byte[] getPhoto2() {
		return photo2;
	}

	public void setPhoto2(byte[] photo2) {
		this.photo2 = photo2;
	}

	public String getPhoto_name2() {
		return photo_name2;
	}

	public void setPhoto_name2(String photo_name2) {
		this.photo_name2 = photo_name2;
	}

	public int getNoti_hit() {
		return noti_hit;
	}

	public void setNoti_hit(int noti_hit) {
		this.noti_hit = noti_hit;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	public String getMem_id() {
		return mem_id;
	}

	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	

	public int getFav_cnt() {
		return fav_cnt;
	}

	public void setFav_cnt(int fav_cnt) {
		this.fav_cnt = fav_cnt;
	}

	@Override
	public String toString() {
		return "NoticeVO [noti_num=" + noti_num + ", status=" + status + ", noti_end=" + noti_end + ", noti_date="
				+ noti_date + ", noti_mdate=" + noti_mdate + ", noti_title=" + noti_title + ", noti_content="
				+ noti_content + ", photo_name1=" + photo_name1 + ", photo_name2=" + photo_name2 + ", noti_hit="
				+ noti_hit + ", mem_num=" + mem_num + ", mem_nickname=" + mem_nickname + ", mem_id=" + mem_id
				+ ", fav_cnt=" + fav_cnt + "]";
	}
}
