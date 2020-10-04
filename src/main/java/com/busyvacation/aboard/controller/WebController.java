package com.busyvacation.aboard.controller;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import com.busyvacation.aboard.db.dto.Member;
import com.busyvacation.aboard.db.repository.CustomPostRepository;
import com.busyvacation.aboard.db.repository.MemberJpaRepository;
import com.busyvacation.aboard.db.repository.PostJpaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class WebController {
	
	@Autowired  
	PostJpaRepository postRepository; 

	@Autowired  
	MemberJpaRepository memberRepository; 

	@Autowired
	CustomPostRepository customPostRepository;
	
	@RequestMapping(value= {"/"})
	
	public String main(final Model model) {

		model.addAttribute("postList",postRepository.findAllByOrderByPostidDesc());
		// model.addAttribute("postList",customPostRepository.get2());
		System.out.println(ZonedDateTime.now());

		// String a = ZonedDateTime.now();
		
		return "index";

	}

		@RequestMapping(value= {"/memberlist"})
		public String sortRecommended(Model model) {
		
			model.addAttribute("memberList",memberRepository.findAll());
			System.out.println(memberRepository.findAll());
			return "memberlist";
		}

}
