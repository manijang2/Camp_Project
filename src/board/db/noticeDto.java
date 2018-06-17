package board.db;

import java.sql.Timestamp;
import java.util.Calendar;

public class noticeDto {
	private int bn_num;			//공지사항 번호
	private int bn_views;		//조회수
	private String bn_title;	//제목
	private String bn_id;		//아이디
	private	String bn_content;	//내용
	private Timestamp bn_date;		//날짜
	
	public int getBn_num() {
		return bn_num;
	}
	public void setBn_num(int bn_num) {
		this.bn_num = bn_num;
	}
	public int getBn_views() {
		return bn_views;
	}
	public void setBn_views(int bn_views) {
		this.bn_views = bn_views;
	}
	public String getBn_title() {
		return bn_title;
	}
	public void setBn_title(String bn_title) {
		this.bn_title = bn_title;
	}
	public String getBn_id() {
		return bn_id;
	}
	public void setBn_id(String bn_id) {
		this.bn_id = bn_id;
	}
	public String getBn_content() {
		return bn_content;
	}
	public void setBn_content(String bn_content) {
		this.bn_content = bn_content;
	}
	public Timestamp getBn_date() {
		return bn_date;
	}
	public void setBn_date(Timestamp bn_date) {
		this.bn_date = bn_date;
	}

	
}
