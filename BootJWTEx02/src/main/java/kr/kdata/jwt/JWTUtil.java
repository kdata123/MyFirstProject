package kr.kdata.jwt;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@SuppressWarnings("unchecked")
public class JWTUtil {
	/*
	 짧으면 다음과 같은 예외가 발생합니다.
	 Caused by: io.jsonwebtoken.security.WeakKeyException: 
	 The specified key byte array is 232 bits which is not secure enough for any JWT HMAC-SHA algorithm.  
	 The JWT JWA Specification (RFC 7518, Section 3.2) states that keys used with HMAC-SHA algorithms 
	 MUST have a size >= 256 bits (the key size must be greater than or equal to the hash output size).  
	 Consider using the io.jsonwebtoken.security.Keys#secretKeyFor(SignatureAlgorithm) method to create a key guaranteed 
	 to be secure enough for your preferred HMAC-SHA algorithm.  See https://tools.ietf.org/html/rfc7518#section-3.2 for more information.
	 */
	static SecretKey secretKey = Keys.hmacShaKeyFor("secret secret secret secret secret secret secret secret".getBytes(StandardCharsets.UTF_8));
	
	public static String makeJwtToken(String userid, String email, String nickName, int minute) {
		Date now = new Date();
		long expire = now.getTime() + Duration.ofMinutes(minute).toMillis();

		return Jwts
				.builder()
				.setHeaderParam(Header.TYPE, Header.JWT_TYPE)
				.setIssuer(userid)
				.setIssuedAt(now)
				.setExpiration(new Date(expire))
				.setSubject(nickName + "(" + email + ")")
				.claim("userID", userid)
				.claim("email", email)
				.claim("nickName", nickName)
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.compact();
	}


	public static Map<String, Object> getJwtTokenClaims(String jwtToken) {
		return (Map<String, Object>) Jwts.parserBuilder().setSigningKey(secretKey).build().parse(jwtToken).getBody();
	}

	public static Map<String, Object> getJwtTokenHeader(String jwtToken) {
		return Jwts.parserBuilder().setSigningKey(secretKey).build().parse(jwtToken).getHeader();
	}

//	public static void main(String[] args) {
//		String jwtToken = makeJwtToken("kdatacademy", "kdatacademy@gmail.com", "kdata", 10);
//		System.out.println(jwtToken);
//		System.out.println(getJwtTokenHeader(jwtToken));
//		System.out.println(getJwtTokenClaims(jwtToken));
//		System.out.println(getJwtTokenClaims(jwtToken).get("sub"));
//		System.out.println(getJwtTokenClaims(jwtToken).get("userID"));
//		System.out.println(getJwtTokenClaims(jwtToken).get("email"));
//		System.out.println(getJwtTokenClaims(jwtToken).get("nickName"));
//	}

}
