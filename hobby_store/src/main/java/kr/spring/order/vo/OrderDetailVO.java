package kr.spring.order.vo;

public class OrderDetailVO {
	private int detail_num;
	private String detail_name;
	private int price;
	private int quantity;
	private int order_num;
	private int course_num;
	private int items_num;
	private int items_total;
	private int space_num;
	
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
	
	@Override
	public String toString() {
		return "OrderDetailVO [detail_num=" + detail_num + ", detail_name=" + detail_name + ", price=" + price
				+ ", quantity=" + quantity + ", order_num=" + order_num + "]";
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
