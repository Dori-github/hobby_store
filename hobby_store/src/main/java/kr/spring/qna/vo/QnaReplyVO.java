package kr.spring.qna.vo;

public class QnaReplyVO {
	private int re_num;
	private String re_content;
	private String re_date;
	private String re_mdate;
	private int qna_num;
	private int mem_num;
	
	private String mem_id;
	private String mem_nickname;
	
	public int getRe_num() {
		return re_num;
	}
	public void setRe_num(int re_num) {
		this.re_num = re_num;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public String getRe_date() {
		return re_date;
	}
	public void setRe_date(String re_date) {
		this.re_date = re_date;
	}
	public String getRe_mdate() {
		return re_mdate;
	}
	public void setRe_mdate(String re_mdate) {
		this.re_mdate = re_mdate;
	}
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}
	@Override
	public String toString() {
		return "QnaReplyVO [re_num=" + re_num + ", re_content=" + re_content + ", re_date=" + re_date + ", re_mdate="
				+ re_mdate + ", qna_num=" + qna_num + ", mem_num=" + mem_num + ", mem_id=" + mem_id + ", mem_nickname="
				+ mem_nickname + "]";
	}
}
