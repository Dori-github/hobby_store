package kr.spring.items.vo;

import java.sql.Date;

public class ItemsVO {
	private int items_num;
	private String cate_num;
	private String items_name;
	private int price;
	private int quantity;
	private String photo1;
	private String photo2;
	private String photo3;
	private Date reg_date;
	private Date mdate;
	private String items_content;
	private int hit;
	private int mem_num;
	
	public int getItems_num() {
		return items_num;
	}
	public void setItems_num(int items_num) {
		this.items_num = items_num;
	}
	public String getCate_num() {
		return cate_num;
	}
	public void setCate_num(String cate_num) {
		this.cate_num = cate_num;
	}
	public String getItems_name() {
		return items_name;
	}
	public void setItems_name(String items_name) {
		this.items_name = items_name;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	@Override
	public String toString() {
		return "ItemsVO [items_num=" + items_num + ", cate_num=" + cate_num + ", items_name=" + items_name + ", price="
				+ price + ", quantity=" + quantity + ", photo1=" + photo1 + ", photo2=" + photo2 + ", photo3=" + photo3
				+ ", reg_date=" + reg_date + ", mdate=" + mdate + ", items_content=" + items_content + ", hit=" + hit
				+ ", mem_num=" + mem_num + "]";
	}
	
	
	
	
		
}
