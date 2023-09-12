package kr.kdata.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	/*
	 * 인증화면이 나오면 application.properties 파일의 사용자/비번을 입력한다.
	 # Security 정보 설정
	 spring.security.user.name=admin
	 spring.security.user.password=123456
	 spring.security.user.roles=USER, ADMIN
	 */
	@RequestMapping(value = {"/","/index","/home"})
	public String index(Model model) {
		LocalDateTime today = LocalDateTime.now();
		model.addAttribute("today", today.format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일(E) hh:mm:ss")));
		return "index";
	}
}
