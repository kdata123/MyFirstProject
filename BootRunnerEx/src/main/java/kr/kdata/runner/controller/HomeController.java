package kr.kdata.runner.controller;

import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
