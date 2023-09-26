package kr.kdata.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SpringBootTest
class BootJwtEx02ApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void createToken() {

		// Header 부분 설정
		Map<String, Object> headers = new HashMap<>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");

		// payload 부분 설정
		Map<String, Object> payloads = new HashMap<>();
		payloads.put("KEY", "HelloWorld");
		payloads.put("NickName", "Erjuer");
		payloads.put("Age", "29");
		payloads.put("TempAuth", "Y");

		// 토큰 유효 시간 (2시간)
		Long expiredTime = 1000 * 60L * 60L * 1L; 
		Date date = new Date(); // 토큰 만료 시간
		date.setTime(date.getTime() + expiredTime);

		SecretKey key = Keys.hmacShaKeyFor("MyNickNameisErjuerAndNameisMinsu".getBytes(StandardCharsets.UTF_8));

		// 토큰 Builder
		String jwt = Jwts.builder().setHeader(headers) // Headers 설정
				.setClaims(payloads) // Claims 설정
				.setSubject("Test") // 토큰 용도
				.setExpiration(date) // 토큰 만료 시간 설정
				.signWith(key, SignatureAlgorithm.HS256).compact(); // 토큰 생성

		System.out.println(">> jwt : " + jwt);
	}
	
	@Test
	public void jwtTest() {
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
	}
}
