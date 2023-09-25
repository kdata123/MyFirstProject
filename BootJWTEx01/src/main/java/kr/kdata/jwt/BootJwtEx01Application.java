package kr.kdata.jwt;

import java.time.Duration;
import java.util.Date;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@SpringBootApplication
public class BootJwtEx01Application {

	public static void main(String[] args) {
		SpringApplication.run(BootJwtEx01Application.class, args);
	}

	@Bean
	public CommandLineRunner getCommandLineRunner() {
		return (args) -> {
			System.out.println("-".repeat(80));
			System.out.println("JWT(Json Web Token) 사용해 보기");
			System.out.println("-".repeat(80));
			String token = makeJwtToken();
			System.out.println(token);
			System.out.println("-".repeat(80));
			Claims claims = parseJwtToken("Bearer " + token);
			System.out.println(claims);
			System.out.println("-".repeat(80));
		};
	}
	// 토큰생성
	public String makeJwtToken() {
		/*
		 * 1. 헤더의 타입(typ)을 지정할 수 있습니다. jwt를 사용하기 때문에 Header.JWT_TYPE로 사용해줍니다. 
		 * 2. 등록된 클레임 중, 토큰 발급자(iss)를 설정할 수 있습니다. 
		 * 3. 등록된 클레임 중, 발급 시간(iat)를 설정할 수 있습니다. Date 타입만  추가가 가능합니다.
		 * 4. 등록된 클레임 중, 만료 시간(exp)을 설정할 수 있습니다. 마찬가지로 Date 타입만 추가가 가능합니다. 
		 * 5. 비공개 클레임을 설정할 수 있습니다. (key-value) 
		 * 6. 해싱 알고리즘과 시크릿 키를 설정할 수 있습니다.
		 */
		Date now = new Date();
		return Jwts.builder().setHeaderParam(Header.TYPE, Header.JWT_TYPE) // (1)
				.setIssuer("kdataacademy") // (2)
				.setIssuedAt(now) // (3)
				.setExpiration(new Date(now.getTime() + Duration.ofMinutes(30).toMillis())) // (4)
				.claim("id", "kdataacademy") // (5)
				.claim("email", "kdataacademy@gmail.com").signWith(SignatureAlgorithm.HS256, "myKey") // (6)
				.compact();
	}
	
	// 토근 파싱
	public Claims parseJwtToken(String authorizationHeader) {
		/*
			1. 헤더가 'Bearer'로 시작하는지 검사합니다.
			2. 'Bearer'을 제외한 문자열만 반환해주도록 처리해줍니다.
			3. 시크릿 키를 넣어주어 토큰을 해석할 수 있습니다.
			4. 해석할 토큰을 문자열(String) 형태로 넣어줍니다.		 
		*/
		if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) { // (1)
			throw new IllegalArgumentException();
		}
		String token = authorizationHeader.substring("Bearer ".length()); // (2)
		return Jwts.parser().setSigningKey("myKey") // (3)
				.parseClaimsJws(token) // (4)
				.getBody();
	}
}
