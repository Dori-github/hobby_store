package kr.spring.space.vo;

import java.util.List;

public class SpaceTimeVO {  
	private int space_num;
	private int mem_num;  
	private String space_reg_date;
	private List<String> space_reg_times;
	private String space_reg_time;

	public String getspace_reg_date() {
		return space_reg_date;
	}
	public void setspace_reg_date(String space_reg_date) {
		this.space_reg_date = space_reg_date;
	}
	public List<String> getspace_reg_times() {
		return space_reg_times;
	}
	public void setspace_reg_times(List<String> space_reg_times) {
		this.space_reg_times = space_reg_times;
	}
	public int getspace_num() {
		return space_num;
	}
	public void setspace_num(int space_num) {
		this.space_num = space_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	public String getspace_reg_time() {
		return space_reg_time;
	}
	public void setspace_reg_time(String space_reg_time) {
		this.space_reg_time = space_reg_time;
	}
	@Override
	public String toString() {
		return "spaceTimeVO [space_num=" + space_num + ", mem_num=" + mem_num + ", space_reg_date="
				+ space_reg_date + ", space_reg_times=" + space_reg_times + "]";
	}
	
	
}
