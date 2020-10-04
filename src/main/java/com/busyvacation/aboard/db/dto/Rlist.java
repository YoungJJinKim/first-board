package com.busyvacation.aboard.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity // DB 와 연결할 클래스
@Table(name="rlist") // 테이블 이름 지정
public class Rlist {
	
	@Id //primarykey
	@Column(name="no", nullable=false)
	private String no;
	
	@Column
	private int postid;

	@Column
	private String memberid;

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public int getPostid() {
		return postid;
	}

	public void setPostid(int postid) {
		this.postid = postid;
	}

	public String getMemberid() {
		return memberid;
	}

	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}

	public String toString(){
        return "No : "+no+"postid"+postid+"memberid"+memberid;
    }
	




	
}
