package kr.kdata.security;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BootSecurityEx01Application {

	public static void main(String[] args) {
		SpringApplication.run(BootSecurityEx01Application.class, args);
	}
	
	@GetMapping("/")
	public String home(Model model) {
		return LocalDateTime.now()
				.format(DateTimeFormatter.ofPattern("yy-MM-dd(E) HH:mm:ss"));
	}

}
