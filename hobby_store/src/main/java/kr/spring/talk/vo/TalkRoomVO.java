package kr.spring.talk.vo;

import java.util.Arrays;

public class TalkRoomVO {
	private int talkroom_num;
	private String talkroom_name;
	private String talkroom_date;
	private int mem_num;
	
	//방의 개수를 counting 
	private int room_cnt;
	//배열로 member을 관리
	private int[] members;
	//메세지를 전체로 가져오기
	private TalkVO talkVO;
	
	public int getTalkroom_num() {
		return talkroom_num;
	}
	public void setTalkroom_num(int talkroom_num) {
		this.talkroom_num = talkroom_num;
	}
	public String getTalkroom_name() {
		return talkroom_name;
	}
	public void setTalkroom_name(String talkroom_name) {
		this.talkroom_name = talkroom_name;
	}
	public String getTalkroom_date() {
		return talkroom_date;
	}
	public void setTalkroom_date(String talkroom_date) {
		this.talkroom_date = talkroom_date;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public int getRoom_cnt() {
		return room_cnt;
	}
	public void setRoom_cnt(int room_cnt) {
		this.room_cnt = room_cnt;
	}
	public int[] getMembers() {
		return members;
	}
	public void setMembers(int[] members) {
		this.members = members;
	}
	public TalkVO getTalkVO() {
		return talkVO;
	}
	public void setTalkVO(TalkVO talkVO) {
		this.talkVO = talkVO;
	}
	@Override
	public String toString() {
		return "TalkRoomVO [talkroom_num=" + talkroom_num + ", talkroom_name=" + talkroom_name + ", talkroom_date="
				+ talkroom_date + ", mem_num=" + mem_num + ", room_cnt=" + room_cnt + ", members="
				+ Arrays.toString(members) + ", talkVO=" + talkVO + "]";
	}
}
