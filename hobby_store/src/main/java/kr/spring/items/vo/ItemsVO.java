package kr.spring.items.vo;

import java.io.IOException;
import java.sql.Date;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

public class ItemsVO {
	private int items_num;
	@NotEmpty
	private int cate_num;
	private int cate_parent;
	private String cate_name;
	@NotEmpty
	private String items_name;
	@Range(min=1,max=1000000)
	private int items_price;
	@Range(min=1,max=999)
	private int items_quantity;
	private int status;
	private byte[] items_photo1;  
	private byte[] items_photo2;
	private byte[] items_photo3;
	private String items_photo_name1;
	private String items_photo_name2;
	private String items_photo_name3;
	private Date reg_date;
	private Date mdate;
	@NotEmpty
	private String items_content;
	private int items_hit;
	private String packaging;
	private String items_zipcode;
	private String items_address1;
	private String items_address2;	
	////////////////////////////////////////////////
	private int mem_num;
	private String mem_nickname;
	///////////////////////////////////////////////
	private int fav_num;
	private int fmem_num;
	private int favcount;
	//////////////////////////////////////////////
	private int reply_num;
	/////////////////////////////////////////////
	private float star_num;
	/////////////////////////////////////////////
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
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

	public int getItems_num() {
		return items_num;
	}

	public void setItems_num(int items_num) {
		this.items_num = items_num;
	}

	public int getCate_num() {
		return cate_num;
	}

	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
	}

	public String getItems_name() {
		return items_name;
	}

	public void setItems_name(String items_name) {
		this.items_name = items_name;
	}

	public int getItems_price() {
		return items_price;
	}

	public void setItems_price(int items_price) {
		this.items_price = items_price;
	}

	public int getItems_quantity() {
		return items_quantity;
	}

	public void setItems_quantity(int items_quantity) {
		this.items_quantity = items_quantity;
	}

	public Date getReg_date() {
		return reg_date;
	}

	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}

	public Date getMdate() {
		return mdate;
	}

	public void setMdate(Date mdate) {
		this.mdate = mdate;
	}

	public String getItems_content() {
		return items_content;
	}

	public void setItems_content(String items_content) {
		this.items_content = items_content;
	}

	public int getMem_num() {
		return mem_num;
	}

	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}

	public byte[] getItems_photo1() {
		return items_photo1;
	}

	public void setItems_photo1(byte[] items_photo1) {
		this.items_photo1 = items_photo1;
	}

	public byte[] getItems_photo2() {
		return items_photo2;
	}

	public void setItems_photo2(byte[] items_photo2) {
		this.items_photo2 = items_photo2;
	}

	public byte[] getItems_photo3() {
		return items_photo3;
	}

	public void setItems_photo3(byte[] items_photo3) {
		this.items_photo3 = items_photo3;
	}

	public String getItems_photo_name1() {
		return items_photo_name1;
	}

	public void setItems_photo_name1(String items_photo_name1) {
		this.items_photo_name1 = items_photo_name1;
	}

	public String getItems_photo_name2() {
		return items_photo_name2;
	}

	public void setItems_photo_name2(String items_photo_name2) {
		this.items_photo_name2 = items_photo_name2;
	}

	public String getItems_photo_name3() {
		return items_photo_name3;
	}

	public void setItems_photo_name3(String items_photo_name3) {
		this.items_photo_name3 = items_photo_name3;
	}

	public int getItems_hit() {
		return items_hit;
	}

	public void setItems_hit(int items_hit) {
		this.items_hit = items_hit;
	}
	
	

	public String getMem_nickname() {
		return mem_nickname;
	}

	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	
	

	public int getFav_num() {
		return fav_num;
	}

	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}

	public int getReply_num() {
		return reply_num;
	}

	public void setReply_num(int reply_num) {
		this.reply_num = reply_num;
	}

	public float getStar_num() {
		return star_num;
	}

	public void setStar_num(float star_num) {
		this.star_num = star_num;
	}
	
	

	public String getPackaging() {
		return packaging;
	}

	public void setPackaging(String packaging) {
		this.packaging = packaging;
	}
	
	

	public String getItems_zipcode() {
		return items_zipcode;
	}

	public void setItems_zipcode(String items_zipcode) {
		this.items_zipcode = items_zipcode;
	}


	public String getItems_address1() {
		return items_address1;
	}

	public void setItems_address1(String items_address1) {
		this.items_address1 = items_address1;
	}

	public String getItems_address2() {
		return items_address2;
	}

	public void setItems_address2(String items_address2) {
		this.items_address2 = items_address2;
	}
	

	public int getFmem_num() {
		return fmem_num;
	}

	public void setFmem_num(int fmem_num) {
		this.fmem_num = fmem_num;
	}
	
	

	public int getFavcount() {
		return favcount;
	}

	public void setFavcount(int favcount) {
		this.favcount = favcount;
	}

	// 업로드 파일 처리
	public void setUpload1(MultipartFile upload1) throws IOException {
		// MultipartFile -> byte[] 변환
		setItems_photo1(upload1.getBytes());
		//파일 이름 	
		setItems_photo_name1(upload1.getOriginalFilename());
	}

	public void setUpload2(MultipartFile upload2) throws IOException {
		// MultipartFile -> byte[] 변환
		setItems_photo2(upload2.getBytes());
		setItems_photo_name2(upload2.getOriginalFilename());
	}

	public void setUpload3(MultipartFile upload3) throws IOException {
		// MultipartFile -> byte[] 변환
		setItems_photo3(upload3.getBytes());
		setItems_photo_name3(upload3.getOriginalFilename());
	
	}

	@Override
	public String toString() {
		return "ItemsVO [items_num=" + items_num + ", cate_num=" + cate_num + ", cate_parent=" + cate_parent
				+ ", cate_name=" + cate_name + ", items_name=" + items_name + ", items_price=" + items_price
				+ ", items_quantity=" + items_quantity + ", status=" + status + ", items_photo_name1="
				+ items_photo_name1 + ", items_photo_name2=" + items_photo_name2 + ", items_photo_name3="
				+ items_photo_name3 + ", reg_date=" + reg_date + ", mdate=" + mdate + ", items_content=" + items_content
				+ ", items_hit=" + items_hit + ", packaging=" + packaging + ", items_zipcode=" + items_zipcode
				+ ", items_address1=" + items_address1 + ", items_address2=" + items_address2 + ", mem_num=" + mem_num
				+ ", mem_nickname=" + mem_nickname + ", fav_num=" + fav_num + ", fmem_num=" + fmem_num + ", favcount="
				+ favcount + ", reply_num=" + reply_num + ", star_num=" + star_num + "]";
	}








	

}
