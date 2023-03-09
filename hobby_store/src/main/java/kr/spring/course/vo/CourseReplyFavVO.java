package kr.spring.course.vo;

public class CourseReplyFavVO {
	private int fav_num;
	private int reply_num;
	private int fmem_num;
	 
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
	public int getFmem_num() {
		return fmem_num;
	}
	public void setFmem_num(int fmem_num) {
		this.fmem_num = fmem_num;
	}
	@Override
	public String toString() {
		return "CourseReplyFavVO [fav_num=" + fav_num + ", reply_num=" + reply_num + ", fmem_num=" + fmem_num + "]";
	}
	
	
}
