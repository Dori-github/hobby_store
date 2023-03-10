package kr.spring.notice.vo;

public class NoticeFavVO {
	private int fav_num;
	private int noti_num;
	private int mem_num;
	
	public int getFav_num() {
		return fav_num;
	}
	public void setFav_num(int fav_num) {
		this.fav_num = fav_num;
	}
	public int getNoti_num() {
		return noti_num;
	}
	public void setNoti_num(int noti_num) {
		this.noti_num = noti_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	@Override
	public String toString() {
		return "BoardFavVO [fav_num=" + fav_num + ", noti_num=" + noti_num + ", mem_num=" + mem_num + "]";
	}
	
	
}
