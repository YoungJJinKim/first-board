package com.busyvacation.aboard.controller;

import java.security.Principal;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.busyvacation.aboard.config.CustomUserDetails;
import com.busyvacation.aboard.db.dto.Member;
import com.busyvacation.aboard.db.dto.Post;
import com.busyvacation.aboard.db.dto.Reply;
import com.busyvacation.aboard.db.dto.Rlist;
import com.busyvacation.aboard.db.repository.MemberJpaRepository;
import com.busyvacation.aboard.db.repository.PostJpaRepository;
import com.busyvacation.aboard.db.repository.ReplyJapRepository;
import com.busyvacation.aboard.db.repository.RlistJpaRepository;
import com.busyvacation.aboard.db.service.StorageService;
import com.busyvacation.aboard.exception.StorageFileNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/posts")
public class PostController {

	@Autowired
	PostJpaRepository postRepository;

	@Autowired
	ReplyJapRepository replyRepository;

	@Autowired
	MemberJpaRepository memberRepository;

	@Autowired
	RlistJpaRepository rlistRepository;
	
	@Autowired  
	private StorageService storageService; 
	
	


	//글 목록(조회수 순)
	@RequestMapping(value= {"/sortviewcount"})
	public String sortRecommended(final Model model) {
		model.addAttribute("postList", postRepository.findAllByOrderByViewcountDesc());
		return "index";
	}

	// 글 목록(추천수 순)
	@RequestMapping(value = { "/sortrecommended" })
	public String sortViewcount(final Model model) {
		model.addAttribute("postList", postRepository.findAllByOrderByRecommendedDesc());
		return "index";
	}

	// 글 쓰기 페이지
	@RequestMapping(value = { "/create", "/create/" })
	public String addForm() {
		return "createPost";
	}

	// 글 쓰기 POST
	@RequestMapping(value = { "", "/" }, method = RequestMethod.POST)
	public String addPost(final Principal user, @RequestParam("title") final String title,@RequestParam("cate")String cate,
			@RequestParam("content") final String content, @RequestParam("file") final MultipartFile file) {

		final Post data = new Post(user.getName(), title, content);

		if (!file.isEmpty()) {
			storageService.store(file);
			final String filename = StringUtils.cleanPath(file.getOriginalFilename());
			
			data.setAttach(filename);
		}
		data.setCate(cate);
		postRepository.saveAndFlush(data);

		return "redirect:/";
	}

	// 글 읽기 + 댓글 리스트 GET
	@RequestMapping(value = { "/{postid}", "/{postid}/" })
	public String show(final Model model, @PathVariable("postid") final int id) {// @PathVariable("postid") int id
		final Post post = postRepository.findByPostid(id);
		post.setViewcount(post.getViewcount() + 1);
		postRepository.saveAndFlush(post);
		model.addAttribute("post", post);
		model.addAttribute("replyList", replyRepository.findByPostid(id));
		// Stream<Path> img =
		// model.addAttribute("files",
		// storageService.loadAll().map(
		// path -> MvcUriComponentsBuilder.fromMethodName(PostController.class,
		// "serveFile", path.getFileName().toString()).build().toString())
		// .collect(Collectors.toList()));

		// model.addAttribute("file",post.getAttach());

		return "show";
	}
	// @RequestMapping(value="/files/{filename:.+}", method=RequestMethod.GET)
	// public ResponseEntity<Resource> serveFile(@PathVariable String filename){
	// // 다운로드
	// Resource file = storageService.loadAsResource(filename);
	// return
	// ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,"attachment;
	// filename=\"" + file.getFilename()+"\"").body(file);
	// }

	// 글 수정 페이지 보기(기존의 글내용 포함)
	@RequestMapping(value = { "/{postid}/modify", "/{postid}/modify/" })
	public String modifyPost(@PathVariable("postid") final int id, final Model model) {
		model.addAttribute("post", postRepository.findByPostid(id));

		return "modify";
	}
	// @RequestMapping(value= {"/{postid}","/{postid}/"}, method=RequestMethod.PUT)
	// @ResponseBody
	// public int modifyCommitPost(@PathVariable("postid") int
	// id,@RequestParam("title") String title,
	// @RequestParam("content") String content) {

	// Post form = postRepository.findByPostid(id);
	// form.setTitle(title);
	// form.setContents(content);
	// postRepository.saveAndFlush(form);

	// return 1;
	// }

	// 글 수정 POST REQUEST
	@RequestMapping(value = { "/{postid}", "/{postid}/" }, method = RequestMethod.POST)
	public String modifyCommitPost2(@PathVariable("postid") final int id, @RequestParam("title") final String title,
			@RequestParam("content") final String content, @Nullable @RequestParam("file") final MultipartFile[] file) {

		final Post form = postRepository.findByPostid(id);
		form.setTitle(title);
		form.setContents(content);

		if (!file[0].isEmpty()) {
			storageService.store(file[0]);
			final String filename = StringUtils.cleanPath(file[0].getOriginalFilename());
			form.setAttach(filename);
		}
		// yyyy/mm/dd

		// String a = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE);

		// a = a.replace("-","/");

		// System.out.println(a);
		// form.setCREATED_DATE(a);

		// ISO_LOCAL_DATE_TIME '2011-12-03T10:15:30'

		String a2 = ZonedDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
		System.out.println("aaaa: " + a2);
		a2 = a2.replace("-", "/");
		a2 = a2.replace("T", " ");

		System.out.println(a2);
		form.setUPDATED_DATE(a2);

		postRepository.saveAndFlush(form);

		return "redirect:/";
	}

	// 글 삭제 DELETE
	@RequestMapping(value = { "/{postid}", "/{postid}/" }, method = RequestMethod.DELETE)
	@ResponseBody
	public int deletePost(@PathVariable("postid") final int id) {
		final Post data = postRepository.findByPostid(id);
		postRepository.delete(data);

		return 1;
	}

	// 댓글 달기
	@RequestMapping(value = { "/{postid}/reply", "/{postid}/reply/" })
	public String addReply(final Model model, final Principal user, @PathVariable("postid") final int id,
			@RequestParam("reply") final String comment) {

		// int postid, String memberid, String comment
		final Reply data = new Reply(id, user.getName(), comment);
		replyRepository.saveAndFlush(data);

		final Post postInfo = postRepository.findByPostid(id);
		final ArrayList<Reply> replyList = replyRepository.findByPostid(id);

		model.addAttribute("post", postInfo);
		model.addAttribute("replyList", replyList);

		return "replyAjax";
	}

	// 댓글 수정
	@RequestMapping(value = { "/{postid}/reply/{replyId}", "/{postid}/reply/{replyId}" }, method = RequestMethod.PUT)
	public String modifyReply(final Model model, @PathVariable("postid") final int postId,
			@PathVariable("replyId") final int replyId, @RequestParam("newReply") final String newReply) {

		System.out.println(postId);
		final Reply form = replyRepository.findByReplyid(replyId);
		form.setComment(newReply);
		replyRepository.saveAndFlush(form);

		final Post postInfo = postRepository.findByPostid(postId);
		final ArrayList<Reply> replyList = replyRepository.findByPostid(postId);

		model.addAttribute("post", postInfo);
		model.addAttribute("replyList", replyList);

		return "replyAjax";
	}

	// 댓글 삭제
	@RequestMapping(value = { "/{postid}/reply/{replyId}", "/{postid}/reply/{replyId}" }, method = RequestMethod.DELETE)
	public String deleteReply(final Model model, @PathVariable("postid") final int postId,
			@PathVariable("replyId") final int replyId) {

		final Reply data = replyRepository.findByReplyid(replyId);
		replyRepository.delete(data);

		final Post postInfo = postRepository.findByPostid(postId);
		final ArrayList<Reply> replyList = replyRepository.findByPostid(postId);

		model.addAttribute("post", postInfo);
		model.addAttribute("replyList", replyList);

		return "replyAjax";
	}

	@GetMapping("/recommend")
	@ResponseBody
	public String recommend(final Model model, Post post, final Principal user) {
		 System.out.println("추천하기눌러습니다.");
		 System.out.println(user.getName());
		 Member member = memberRepository.findByMemberid(user.getName());
		
		
			post = postRepository.findByPostid(post.getPostid());
		
		Rlist rlist = rlistRepository.findByMemberidAndPostid(user.getName(),post.getPostid());
		


		if(rlist != null){
			System.out.println("이미추천한 사용자입니다.");
		
		}
		else if(member.getRole()==0){
			
			post = postRepository.findByPostid(post.getPostid());
			post.setRecommended(post.getRecommended()+1);
			postRepository.saveAndFlush(post);		
		
			Rlist rlist2 = new Rlist();

			rlist2.setMemberid(user.getName());
			rlist2.setNo(member.getNo());
			rlist2.setPostid(post.getPostid());

			rlistRepository.saveAndFlush(rlist2);
			
		}else if(member.getRole()==1){

			System.out.println("전문가입니다.");
			post.setMrecommended(post.getMrecommended()+1);
			postRepository.saveAndFlush(post);
				
					
			Rlist rlist2 = new Rlist();

			rlist2.setMemberid(user.getName());
			rlist2.setNo(member.getNo());
			rlist2.setPostid(post.getPostid());

			rlistRepository.saveAndFlush(rlist2);
			
		}



		return Integer.toString(post.getRecommended());
	}

	@ExceptionHandler(StorageFileNotFoundException.class)
	public ResponseEntity<?> handleStorageFileNotFound(final StorageFileNotFoundException exc) {
		return ResponseEntity.notFound().build();
	}
}
