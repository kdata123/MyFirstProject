package kr.kdata.jwt;

import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootJwtEx02Application {

	public static void main(String[] args) {
		SpringApplication.run(BootJwtEx02Application.class, args);
	}

	@Bean
	public CommandLineRunner getCommandLineRunner() {
		return (args) -> {
			System.out.println("-".repeat(80));
			System.out.println("JWT(Json Web Token) 사용해 보기");
			System.out.println("-".repeat(80));
			String jwtToken = JWTUtil.makeJwtToken("kdatacademy", "kdatacademy@gmail.com", "kdata", 10);
			System.out.println(jwtToken);
			System.out.println(JWTUtil.getJwtTokenHeader(jwtToken));
			Map<String, Object> map = JWTUtil.getJwtTokenClaims(jwtToken);
			System.out.println(map);
			System.out.println(map.get("sub"));
			System.out.println(map.get("userID"));
			System.out.println(map.get("email"));
			System.out.println(map.get("nickName"));
		};
	}

}
