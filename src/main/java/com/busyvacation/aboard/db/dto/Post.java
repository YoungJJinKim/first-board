package com.busyvacation.aboard.db.dto;

import java.math.BigDecimal;
import java.sql.Timestamp;
// import com.sun.proxy.$Proxy159;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


import org.hibernate.annotations.DynamicInsert;

@DynamicInsert
@Entity // DB 와 연결할 클래스
@Table(name="post") // 테이블 이름 지정
public class Post {
	@Id
	@Column(nullable=true)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="POST_SEQ_GENERATOR")
    @SequenceGenerator(name="POST_SEQ_GENERATOR", sequenceName="POST_SEQ", allocationSize = 1)
	private int postid;
	@Column
	private String title;
	@Column
	private String memberid;
	@Column(nullable = true)
	private String attach;
	@Column(nullable = true)
	private String CREATED_DATE;
	@Column(nullable = true)
	private String UPDATED_DATE;
	@Column
	private String contents;
	@Column(nullable = true)
	private int viewcount;
	@Column(nullable = true)
	private int recommended;
	@Column(name="M_RECOMMENDED", nullable = true)
	private int mrecommended;
	@Column(nullable = true)
	private String cate;
	
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMemberid() {
		return memberid;
	}
	public void setMemberid(String memberid) {
		this.memberid = memberid;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public String getAttach() {
		return attach;
	}
	public void setAttach(String attach) {
		this.attach = attach;
	}
	
	public String getCREATED_DATE() {
		String str = this.CREATED_DATE;
    	if(str != null) {
    	String[] split = str.split(" ");
     	return split[0]; }
    	else {
    		return this.CREATED_DATE;
		}
		
	}

	public void setCREATED_DATE(String cREATED_DATE) {
		CREATED_DATE = cREATED_DATE;
	}

	public String getUPDATED_DATE() {
		return UPDATED_DATE;
	}

	public void setUPDATED_DATE(String uPDATED_DATE) {
		UPDATED_DATE = uPDATED_DATE;
	}

	public int getViewcount() {
		return this.viewcount;
	}

	public void setViewcount(int viewcount) {
		this.viewcount = viewcount;
	}

	public int getRecommended() {
		return this.recommended;
	}

	public void setRecommended(int recommended) {
		this.recommended = recommended;
	}


	
	public int getMrecommended() {
		return this.mrecommended;
	}

	public void setMrecommended(int recommended) {
		this.mrecommended = recommended;
	}



	//생성자 리스트
	public Post(BigDecimal postid, String title, String memberid, String attach, Timestamp cREATED_DATE,
	Timestamp uPDATED_DATE, String contents) {
		this.postid = postid.intValue();
		this.title = title;
		this.memberid = memberid;
		this.contents = contents;
		this.attach = attach;
		CREATED_DATE = cREATED_DATE.toString();
		UPDATED_DATE = uPDATED_DATE.toString();
	}

	public Post(){};
	public Post(int postid, String title, String memberid, String contents, String attach) {
		this.postid = postid;
		this.title = title;
		this.memberid = memberid;
		this.contents = contents;
		this.attach = attach;
	}

	public Post(String memberid, String title, String contents){
		this.title = title;
		this.memberid = memberid;
		this.contents = contents;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

}
