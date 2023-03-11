package kr.spring.space.vo;

public class SpaceFavVO {
	private int fav_num;
	private int space_num;
	private int fmem_num;
	private int mem_num;
	
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}
	public int getSpace_num() {
		return space_num;
	}
	public void setSpace_num(int space_num) {
		this.space_num = space_num;
	}
	public int getFmem_num() {
		return fmem_num;
	}
	public void setFmem_num(int fmem_num) {
		this.fmem_num = fmem_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	@Override
	public String toString() {
		return "SpaceFavVO [fav_num=" + fav_num + ", space_num=" + space_num + ", fmem_num=" + fmem_num + ", mem_num="
				+ mem_num + "]";
	}
	

	

}
