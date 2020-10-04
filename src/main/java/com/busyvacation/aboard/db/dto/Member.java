package com.busyvacation.aboard.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity // DB 와 연결할 클래스
@Table(name="member") // 테이블 이름 지정
public class Member {
	
	@Id //primarykey
	@Column(name="no", nullable=false)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="MEMBER_SEQ_GENERATOR")
    @SequenceGenerator(name="MEMBER_SEQ_GENERATOR", sequenceName="MEMBER_SEQ", allocationSize = 1)
	private String no;
	
	@Column
	private String memberid;
	
	@Column
	private String password;

	@Column
	private int role;

	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

	public String toString(){
        return "No : "+no+"role"+role;
    }
	
}
