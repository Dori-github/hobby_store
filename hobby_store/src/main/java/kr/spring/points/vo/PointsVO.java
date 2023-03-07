package kr.spring.points.vo;

import java.sql.Date;

public class PointsVO {
	private int points_num;
	private int saved_points;
	private int used_points;
	private int points_type;
	private Date saved_date;
	private String expired_date;
	private Date used_date;
	private int mem_num;
	
	public int getPoints_num() {
		return points_num;
	}
	public void setPoints_num(int points_num) {
		this.points_num = points_num;
	}
	public int getSaved_points() {
		return saved_points;
	}
	public void setSaved_points(int saved_points) {
		this.saved_points = saved_points;
	}
	public int getUsed_points() {
		return used_points;
	}
	public void setUsed_points(int used_points) {
		this.used_points = used_points;
	}
	public int getPoints_type() {
		return points_type;
	}
	public void setPoints_type(int points_type) {
		this.points_type = points_type;
	}
	public Date getSaved_date() {
		return saved_date;
	}
	public void setSaved_date(Date saved_date) {
		this.saved_date = saved_date;
	}
	public String getExpired_date() {
		return expired_date;
	}
	public void setExpired_date(String expired_date) {
		this.expired_date = expired_date;
	}
	public Date getUsed_date() {
		return used_date;
	}
	public void setUsed_date(Date used_date) {
		this.used_date = used_date;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	
	@Override
	public String toString() {
		return "PointsVO [points_num=" + points_num + ", saved_points=" + saved_points + ", used_points=" + used_points
				+ ", points_type=" + points_type + ", saved_date=" + saved_date + ", expired_date=" + expired_date
				+ ", used_date=" + used_date + ", mem_num=" + mem_num + "]";
	}
	
}
