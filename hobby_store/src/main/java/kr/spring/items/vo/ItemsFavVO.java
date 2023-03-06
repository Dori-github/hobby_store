package kr.spring.items.vo;

public class ItemsFavVO {
	
	private int fav_num;
	private int items_num;
	private int fmem_num;
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}
	public int getItems_num() {
		return items_num;
	}
	public void setItems_num(int items_num) {
		this.items_num = items_num;
	}
	public int getFmem_num() {
		return fmem_num;
	}
	public void setFmem_num(int fmem_num) {
		this.fmem_num = fmem_num;
	}
	@Override
	public String toString() {
		return "ItemsFavVO [fav_num=" + fav_num + ", items_num=" + items_num + ", fmem_num=" + fmem_num + "]";
	}
	
	
	
	
}
