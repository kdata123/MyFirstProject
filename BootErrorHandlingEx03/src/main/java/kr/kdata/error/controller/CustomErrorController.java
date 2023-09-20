package kr.kdata.error.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String error(HttpServletRequest request, Model model) {
        // Boot 2.X대는 : javax.servlet.error
    	// Object status = request.getAttribute("javax.servlet.error.status_code");
    	// Boot 3.X때는 : jakarta.servlet.error
        Object status = request.getAttribute("jakarta.servlet.error.status_code");

        model.addAttribute("status", "상태:" + status);
        model.addAttribute("path", request.getAttribute("jakarta.servlet.error.request_uri"));
        model.addAttribute("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년 MM월 dd일(EEE) hh:mm:ss")));

        Object exceptionObj = request.getAttribute("jakarta.servlet.error.exception");
        if (exceptionObj != null) {
            Throwable e = ((Exception) exceptionObj).getCause();
            model.addAttribute("exception", e.getClass().getName());
            model.addAttribute("message", e.getMessage());
        }

        if (status.equals(HttpStatus.NOT_FOUND.value())) {
            return "/error/404";
        } else if (status.equals(405)) {
            return "/error/405";
        } else {
            return "/error/500";
        }
    }
    
    @GetMapping(value = {"/accessDenied"})
	public String accessDenied() {
		return "/error/accessDenied";
	}

	// 첫번째 오류: 0으로 나눴으므로 500 오류를 발생시킨다.
	@GetMapping("/div")
	public String problem(Model model) {
		int result = 1 / 0;
		model.addAttribute("serverTime", result);
		return "home";
	}

	// 두 번째 오류: @PostMapping이므로 Get 방식으로 요청 시 405 오류를 발생시킨다.
	@PostMapping("/method")
	public String method(Model model) {
		return "home";
	}
}