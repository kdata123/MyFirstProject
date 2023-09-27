package kr.kdata.header;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HeaderAPIController {
	// 요청 헤더값 읽기
	@GetMapping("/test1")
	public String test1(@RequestHeader HttpHeaders headers) {
		return "headers : " + headers;
	}
	
	@GetMapping("/test2")
	public String test2(@RequestHeader HttpHeaders headers) {
		MultiValueMap<String, String> headersMap = headers;
		StringBuffer sb = new StringBuffer();
		for(String key : headersMap.keySet()) {
			sb.append("키 : " + key + "<br>");
			sb.append("값 : " + headersMap.get(key) + "<br>");
			sb.append("<hr>");
		}
		return sb.toString();
	}
	@GetMapping("/test3")
	public ResponseEntity<String> test3(@RequestHeader HttpHeaders headers) {
		// 요청헤더읽기
		String name = headers.getFirst("Name");
		String email = headers.getFirst("email");
		String nickname = headers.getFirst("nickname");
		System.out.println(name + ", " + email + ", " + nickname);
		return ResponseEntity.ok()
	    		// 요청한 헤더정보를 다시 응답헤더 추가해서 응답
	    		.header("userid", name)
	    		.header("nickname", nickname)
	    		.header("email", email)
	    		.body(headers.getFirst("HEADER"));
	}
	
	// 헤더값 추가 해서 전송하기
	@GetMapping("/header1")
	public ResponseEntity<String> getHeader1(@RequestHeader HttpHeaders headers) {
	    return ResponseEntity.ok().body(headers.getFirst("HEADER"));
	}
	
	@GetMapping("/header2")
	public ResponseEntity<String> getHeader2(@RequestHeader Map<String, String> data) throws UnsupportedEncodingException{
	    return ResponseEntity.ok()
	    		// 응답헤더 추가
	    		.header("userid", "kdataacademy")
	    		.header("nickname", URLEncoder.encode("최고관리자","UTF-8"))
	    		.header("email", "kdataacademy@gmail.com")
	    		.body(data.get("header"));
	}	
}
