package com.busyvacation.aboard.db.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity // DB 와 연결할 클래스
@Table(name="reply_maria") // 테이블 이름 지정
public class Reply {
	@Id
	@Column(nullable=true)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ_GENERATOR")
    @SequenceGenerator(name="POST_SEQ_GENERATOR", sequenceName="POST_SEQ", allocationSize = 1)
	private int replyid;
	
	@Column
	private int postid;
	@Column
	private String memberid;
	@Column
	private String comments;
	
	public Reply() {}
	
	public Reply(int postid, String memberid, String comment) {
		this.postid = postid;
		this.memberid = memberid;
		this.comments = comment;
	}
	
	
	public int getReplyid() {
		return replyid;
	}
	public void setReplyid(int replyid) {
		this.replyid = replyid;
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
	public String getComment() {
		return comments;
	}
	public void setComment(String comment) {
		this.comments = comment;
	}
}
