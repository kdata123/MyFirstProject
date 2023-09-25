package kr.kdata.filter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@ServletComponentScan
@SpringBootApplication
@Controller
public class BootFilterEx01Application {

	public static void main(String[] args) {
		SpringApplication.run(BootFilterEx01Application.class, args);
	}

	@GetMapping(value = {"/","/index","/main/index"})
	public String home(Model model) {
		model.addAttribute("today", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd(E) hh:mm:ss.S")));
		return "index";
	}
	
	// 필터를 등록한다.
	@Bean
	public FilterRegistrationBean<MyFilter1> setFilterRegistration1() {
		FilterRegistrationBean<MyFilter1> filterRegistrationBean = new FilterRegistrationBean<>(new MyFilter1());
		// filterRegistrationBean 의 "setUrlPatterns" 와 "addUrlPatterns"의 차이는 별거 없다.
		// filterRegistrationBean.setUrlPatterns(Collections.singletonList("/filtered/*")); // 리스트를 받는 메서드
		filterRegistrationBean.addUrlPatterns("/*"); // string 여러개를 가변인자로 받는 메소드
		return filterRegistrationBean;
	}
	@Bean
	public FilterRegistrationBean<MyFilter2> setFilterRegistration2() {
		FilterRegistrationBean<MyFilter2> filterRegistrationBean = new FilterRegistrationBean<>(new MyFilter2());
		filterRegistrationBean.addUrlPatterns("/main/*"); // string 여러개를 가변인자로 받는 메소드
		return filterRegistrationBean;
	}
}
