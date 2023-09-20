package kr.kdata.error.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@GetMapping(value = "/")
	public String getToday(Model model) {
		Date today = new Date();
		log.info("serverTime : {}", today);
		model.addAttribute("serverTime", today);
		return "index";
	}
	@GetMapping(value = "/today1")
	@ResponseBody
	public String today1() {
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("현재 1 : yyyy-MM-dd(E) hh:mm:ss"));
	}
	/*

	@GetMapping(value = "/today2")
	@ResponseBody
	public String today2() throws InterruptedException {
		Thread.sleep(1000);
		return LocalDateTime.now().format(DateTimeFormatter.ofPattern("현재 2 : yyyy-MM-dd(E) hh:mm:ss"));
	}
	*/
	@GetMapping(value = "/hello")
	@ResponseBody
	public String hello(@RequestParam(defaultValue = "손")String name) {
		return name + "님 반갑습니다.";
	}

}
