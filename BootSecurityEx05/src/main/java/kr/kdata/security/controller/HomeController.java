package kr.kdata.security.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.kdata.security.service.MemberService;
import kr.kdata.security.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {

	@RequestMapping(value = {"/","/index","/home"})
	public String index(Model model) {
		model.addAttribute("serverTime", getToday());
		return "index";
	}
	
	@GetMapping(value = "/today")
	@ResponseBody
	public String today1() {
		return "현재 시간 : " + getToday();
	}

	
	@RequestMapping(value = {"/login"})
	public String login(Model model) {
		model.addAttribute("serverTime", getToday());
		return "login";
	}

	
	@RequestMapping(value = {"/hello"})
	public String hello(Model model) {
		model.addAttribute("serverTime", getToday());
		return "hello";
	}
	
	@RequestMapping(value = {"/member"})
	public String memeber(Model model) {
		model.addAttribute("serverTime", getToday());
		return "member";
	}
	
	@RequestMapping(value = {"/admin"})
	public String admin(Model model) {
		model.addAttribute("serverTime", getToday());
		return "admin";
	}

	@GetMapping(value = {"/accessDenied"})
	public String accessDenied() {
		return "accessDenied";
	}
	
	// 회원 가입 폼
	@GetMapping(value = {"/join"})
	public String join() {
		return "join";
	}
	

	// 회원 가입 완료(Get)
	@GetMapping(value = {"/joinOk"})
	public String joinOkGet() {
		return "join";
	}
	
	@Autowired
	MemberService memberService;
	
	// 회원 가입 완료(Post)
	@PostMapping(value = {"/joinOk"})
	public String joinOkPost(@ModelAttribute Users users){
		if(memberService.joinOk(users)) {
			log.info("{} 회원가입 완료!!", users);
		}else {
			log.info("{} 회원가입 실패!!", users);
		}
		return "redirect:/";
	}
	
	
	private String getToday() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일(EEEE) hh시 mm분 ss.S초"));
	}
}
