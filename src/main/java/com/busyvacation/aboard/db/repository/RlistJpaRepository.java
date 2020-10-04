package com.busyvacation.aboard.db.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

import javax.transaction.Transactional;

import com.busyvacation.aboard.db.dto.Member;
import com.busyvacation.aboard.db.dto.Rlist;


public interface RlistJpaRepository extends JpaRepository<Rlist, String>{
	public Rlist findByMemberid(String name);
	public Rlist findByNo(String No);
	public Rlist findByPostid(int i);
	public Rlist findByMemberidAndPostid(String memberid, int Postid);

	// @Transactional
	// @Modifying			 
	// @Query("select * from rlist where postid= :postid AND memberid= :memberid")
	// public Rlist find(@Param("postid") String postid , @Param("memberid") String memberid);

	// public List<Rlist> findByPostid(int i);
}
