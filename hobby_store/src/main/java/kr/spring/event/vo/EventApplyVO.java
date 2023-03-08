package kr.spring.event.vo;

import java.sql.Date;

public class EventApplyVO {
	
	private int event_a_num;
	private int event_num;
	private int mem_num;
	private Date event_a_date;
	private int event_a_win;
	
	public int getEvent_a_num() {
		return event_a_num;
	}
	public void setEvent_a_num(int event_a_num) {
		this.event_a_num = event_a_num;
	}
	public int getEvent_num() {
		return event_num;
	}
	public void setEvent_num(int event_num) {
		this.event_num = event_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public Date getEvent_a_date() {
		return event_a_date;
	}
	public void setEvent_a_date(Date event_a_date) {
		this.event_a_date = event_a_date;
	}
	public int getEvent_a_win() {
		return event_a_win;
	}
	public void setEvent_a_win(int event_a_win) {
		this.event_a_win = event_a_win;
	}
	
	@Override
	public String toString() {
		return "EventApplyVO [event_a_num=" + event_a_num + ", event_num=" + event_num + ", mem_num=" + mem_num
				+ ", event_a_date=" + event_a_date + ", event_a_win=" + event_a_win + "]";
	}
}
