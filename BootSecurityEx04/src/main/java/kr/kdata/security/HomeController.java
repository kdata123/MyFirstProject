package kr.kdata.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
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
	
	private String getToday() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일(EEEE) hh시 mm분 ss.S초"));
	}
}
