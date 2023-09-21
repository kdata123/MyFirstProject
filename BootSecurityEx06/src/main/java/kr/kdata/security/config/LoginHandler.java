package kr.kdata.security.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.kdata.security.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
public class LoginHandler implements AuthenticationSuccessHandler {

	private final MemberService memberService;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 로그인 내용을 세션에 저장한다.
		request.getSession().setAttribute("mvo", memberService.loadUserByUsername(authentication.getName()));	
		log.info("-".repeat(60));
		log.info("로그인 후 실행");
		log.info("-".repeat(60));
		response.sendRedirect("/");
	}

}
