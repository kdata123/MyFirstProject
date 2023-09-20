package kr.kdata.error;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootErrorHandlingEx03Application {

	public static void main(String[] args) {
		SpringApplication.run(BootErrorHandlingEx03Application.class, args);
	}
	@Bean
	CommandLineRunner getCommandLineRunner1() {
		return (args)->{// 람다식 사용
			System.out.println("-".repeat(80));
			System.out.println("http://localhost:8080 으로 접속해주세요");
			System.out.println("문서화 : http://localhost:8080/swagger-ui/index.html");
			System.out.println("-".repeat(80));
		};
	}

}
