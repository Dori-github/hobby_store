package kr.spring.cart.vo;

public class ItemCartVO {
	private int cart_num;
	private int quantity;
	private int mem_num;
	private int items_num;
	private int items_total;
	
	private int cate_parent;
	private String cate_name;
	private String items_photo1;
	private String items_name;
	private int items_price;
	private int items_quantity;
	
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
	public int getItems_num() {
		return items_num;
	}
	public void setItems_num(int items_num) {
		this.items_num = items_num;
	}
	public int getItems_total() {
		return items_total;
	}
	public void setItems_total(int items_total) {
		this.items_total = items_total;
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
	public String getItems_photo1() {
		return items_photo1;
	}
	public void setItems_photo1(String items_photo1) {
		this.items_photo1 = items_photo1;
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
	
	@Override
	public String toString() {
		return "ItemCartVO [cart_num=" + cart_num + ", quantity=" + quantity + ", mem_num=" + mem_num + ", items_num="
				+ items_num + ", items_total=" + items_total + ", cate_parent=" + cate_parent + ", cate_name="
				+ cate_name + ", items_photo1=" + items_photo1 + ", items_name=" + items_name + ", items_price="
				+ items_price + ", items_quantity=" + items_quantity + "]";
	}
}
