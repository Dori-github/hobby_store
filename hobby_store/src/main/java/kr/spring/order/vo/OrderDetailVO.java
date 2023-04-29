package kr.spring.order.vo;

import org.springframework.stereotype.Component;

@Component("orderList")
public class OrderDetailVO {
	private int detail_num;
	private String detail_name;
	private int price;
	private int quantity;
	private int order_num;
	private int course_num;
	private String c_date;
	private String c_time;
	private int items_num;
	private int items_total;
	private int space_num;
	private int res_sum;
	
	public int getDetail_num() {
		return detail_num;
	}
	public void setDetail_num(int detail_num) {
		this.detail_num = detail_num;
	}
	public String getDetail_name() {
		return detail_name;
	}
	public void setDetail_name(String detail_name) {
		this.detail_name = detail_name;
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
	public int getOrder_num() {
		return order_num;
	}
	public void setOrder_num(int order_num) {
		this.order_num = order_num;
	}
	public String getC_date() {
		return c_date;
	}
	public void setC_date(String c_date) {
		this.c_date = c_date;
	}
	public String getC_time() {
		return c_time;
	}
	public void setC_time(String c_time) {
		this.c_time = c_time;
	}
	
	public int getRes_sum() {
		return res_sum;
	}
	public void setRes_sum(int res_sum) {
		this.res_sum = res_sum;
	}
	@Override
	public String toString() {
		return "OrderDetailVO [detail_num=" + detail_num + ", detail_name=" + detail_name + ", price=" + price
				+ ", quantity=" + quantity + ", order_num=" + order_num + ", course_num=" + course_num + ", c_date="
				+ c_date + ", c_time=" + c_time + ", items_num=" + items_num + ", items_total=" + items_total
				+ ", space_num=" + space_num + "]";
	}
	public int getItems_total() {
		return items_total;
	}
	public void setItems_total(int items_total) {
		this.items_total = items_total;
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
}
