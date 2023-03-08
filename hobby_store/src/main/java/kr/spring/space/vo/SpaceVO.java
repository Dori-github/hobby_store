package kr.spring.space.vo;

import java.io.IOException;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Range;
import org.springframework.web.multipart.MultipartFile;

import kr.spring.course.vo.CourseTimeVO;

public class SpaceVO {
	private int space_num;

	@NotEmpty
	private String space_name;
	@NotEmpty
	private String space_content; 
	private int space_hit;
	@Range(min=0,max=99999999)
	private int space_limit;//공간재고 (==delivery limitXX) ==quantity재고
	private Date space_date;
	
	private byte[] space_photo;
	private byte[] space_photo1;
	private byte[] space_photo2;
	private byte[] space_photo3;
	private String space_zipcode;
	private String space_address1;
	private String space_address2;
	@Range(min=0,max=999999)
	private int space_np;//예약인원
	@Range(min=0,max=99999999)
	private int space_month;
	private int space_count;
	private int space_price;
	private Date date;
	private Date mdate;
	
	private int mem_num;
	private String id;
	private String mem_nickname;
	
	private String space_photo_name;
	private String space_photo_name1;
	private String space_photo_name2;
	private String space_photo_name3;
    

	private String space_reg_date;
	private String space_reg_time;
    private int fav_num;//좋아요 갯수 
    private int reply_num;//후기 갯수
    private double star;//별점평균
    private int cate_num;
    private int fav_cnt;
    
    private List<SpaceTimeVO> spaceTimeVO;
    
    
    
    
    
    
    public int getSpace_month() {
		return space_month;
	}
	public void setSpace_month(int space_month) {
		this.space_month = space_month;
	}
	public int getSpace_count() {
		return space_count;
	}
	public void setSpace_count(int space_count) {
		this.space_count = space_count;
	}
	public int getCate_num() {
		return cate_num;
	}
	public void setCate_num(int cate_num) {
		this.cate_num = cate_num;
	}
	//업로드 파일 처리
  	public void setUpload(MultipartFile upload) 
              throws IOException {
    //MultipartFile -> byte[] 변환
    setSpace_photo(upload.getBytes());
    //파일명 구하기 
    setSpace_photo_name(upload.getOriginalFilename());
    }
  	public void setUpload1(MultipartFile upload1) 
  			                   throws IOException {
  		//MultipartFile -> byte[] 변환
  		setSpace_photo1(upload1.getBytes());
  		//파일명 구하기
  		setSpace_photo_name1(upload1.getOriginalFilename());
  	}
  	
  	public void setUpload2(MultipartFile upload2) 
  			                    throws IOException {
  		//MultipartFile -> byte[] 변환
  		setSpace_photo2(upload2.getBytes());
  		//파일명 구하기
  		setSpace_photo_name2(upload2.getOriginalFilename());
  	}
  	public void setUpload3(MultipartFile upload3) 
              throws IOException {
        //MultipartFile -> byte[] 변환
        setSpace_photo3(upload3.getBytes());
        //파일명 구하기
        setSpace_photo_name3(upload3.getOriginalFilename());
        
     }
	public int getSpace_num() {
		return space_num;
	}
	public void setSpace_num(int space_num) {
		this.space_num = space_num;
	}
	public String getSpace_name() {
		return space_name;
	}
	public void setSpace_name(String space_name) {
		this.space_name = space_name;
	}
	public String getSpace_content() {
		return space_content;
	}
	public void setSpace_content(String space_content) {
		this.space_content = space_content;
	}
	public int getSpace_hit() {
		return space_hit;
	}
	public void setSpace_hit(int space_hit) {
		this.space_hit = space_hit;
	}
	public int getSpace_limit() {
		return space_limit;
	}
	public void setSpace_limit(int space_limit) {
		this.space_limit = space_limit;
	}
	public Date getSpace_date() {
		return space_date;
	}
	public void setSpace_date(Date space_date) {
		this.space_date = space_date;
	}
	public byte[] getSpace_photo() {
		return space_photo;
	}
	public void setSpace_photo(byte[] space_photo) {
		this.space_photo = space_photo;
	}
	public byte[] getSpace_photo1() {
		return space_photo1;
	}
	public void setSpace_photo1(byte[] space_photo1) {
		this.space_photo1 = space_photo1;
	}
	public byte[] getSpace_photo2() {
		return space_photo2;
	}
	public void setSpace_photo2(byte[] space_photo2) {
		this.space_photo2 = space_photo2;
	}
	public byte[] getSpace_photo3() {
		return space_photo3;
	}
	public void setSpace_photo3(byte[] space_photo3) {
		this.space_photo3 = space_photo3;
	}
	public String getSpace_zipcode() {
		return space_zipcode;
	}
	public void setSpace_zipcode(String space_zipcode) {
		this.space_zipcode = space_zipcode;
	}
	public String getSpace_address1() {
		return space_address1;
	}
	public void setSpace_address1(String space_address1) {
		this.space_address1 = space_address1;
	}
	public String getSpace_address2() {
		return space_address2;
	}
	public void setSpace_address2(String space_address2) {
		this.space_address2 = space_address2;
	}
	public int getSpace_np() {
		return space_np;
	}
	public void setSpace_np(int space_np) {
		this.space_np = space_np;
	}
	public int getSpace_price() {
		return space_price;
	}
	public void setSpace_price(int space_price) {
		this.space_price = space_price;
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
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String nick_name) {
		this.mem_nickname = nick_name;
	}
	public String getSpace_photo_name() {
		return space_photo_name;
	}
	public void setSpace_photo_name(String space_photo_name) {
		this.space_photo_name = space_photo_name;
	}
	public String getSpace_photo_name1() {
		return space_photo_name1;
	}
	public void setSpace_photo_name1(String space_photo_name1) {
		this.space_photo_name1 = space_photo_name1;
	}
	public String getSpace_photo_name2() {
		return space_photo_name2;
	}
	public void setSpace_photo_name2(String space_photo_name2) {
		this.space_photo_name2 = space_photo_name2;
	}
	public String getSpace_photo_name3() {
		return space_photo_name3;
	}
	public void setSpace_photo_name3(String space_photo_name3) {
		this.space_photo_name3 = space_photo_name3;
	}
	public String getSpace_reg_date() {
		return space_reg_date;
	}
	public void setSpace_reg_date(String space_reg_date) {
		this.space_reg_date = space_reg_date;
	}
	public String getSpace_reg_time() {
		return space_reg_time;
	}
	public void setSpace_reg_time(String space_reg_time) {
		this.space_reg_time = space_reg_time;
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

	public double getStar() {
		return star;
	}
	public void setStar(double star) {
		this.star = star;
	}
	
	public int getFav_cnt() {
		return fav_cnt;
	}
	public void setFav_cnt(int fav_cnt) {
		this.fav_cnt = fav_cnt;
	}
	
	
	public List<SpaceTimeVO> getSpaceTimeVO() {
		return spaceTimeVO;
	}
	public void setSpaceTimeVO(List<SpaceTimeVO> spaceTimeVO) {
		this.spaceTimeVO = spaceTimeVO;
	}
	@Override
	public String toString() {
		return "SpaceVO [space_num=" + space_num + ", space_name=" + space_name + ", space_content=" + space_content
				+ ", space_hit=" + space_hit + ", space_limit=" + space_limit + ", space_date=" + space_date
				+ ", space_zipcode=" + space_zipcode + ", space_address1=" + space_address1 + ", space_address2="
				+ space_address2 + ", space_np=" + space_np + ", space_price=" + space_price + ", date=" + date
				+ ", mdate=" + mdate + ", mem_num=" + mem_num + ", id=" + id + ", mem_nickname=" + mem_nickname
				+ ", space_photo_name=" + space_photo_name + ", space_photo_name1=" + space_photo_name1
				+ ", space_photo_name2=" + space_photo_name2 + ", space_photo_name3=" + space_photo_name3
				+ ", space_reg_date=" + space_reg_date + ", space_reg_time=" + space_reg_time + ", fav_num=" + fav_num
				+ ", reply_num=" + reply_num + ", star=" + star + ", cate_num=" + cate_num + ", fav_cnt=" + fav_cnt
				+ ", spaceTimeVO=" + spaceTimeVO + "]";
	}
	
}