package kr.spring.cart.vo;

public class CourseCartVO {
	private int cart_num;
	private int quantity;
	private int mem_num;
	private int course_num;
	
	public int getCart_num() {
		return cart_num;
	}
	public void setCart_num(int cart_num) {
		this.cart_num = cart_num;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getCourse_num() {
		return course_num;
	}
	public void setCourse_num(int course_num) {
		this.course_num = course_num;
	}
	
	@Override
	public String toString() {
		return "CourseCartVO [cart_num=" + cart_num + ", quantity=" + quantity + ", mem_num=" + mem_num
				+ ", course_num=" + course_num + "]";
	}
}
