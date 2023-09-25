package kr.kdata.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @WebFilter(urlPatterns = "/*") // 이것만 쓰면 안됨
// @Component // 이것을 쓰면 모든 url에 필터 적용. urlPatterns 적용안됨
// 그럼 어떻게? FilterRegistrationBean을 등록한다.
// 이유 : https://velog.io/@ansalstmd/%EC%8A%A4%ED%94%84%EB%A7%81%EB%B6%80%ED%8A%B8-%EB%8B%A4%EC%96%91%ED%95%9C-%EA%B8%B0%EB%8A%A5-5.-Spring-Boot-Filter%EC%99%80-Interceptor#02-getreader-has-already-been-called-for-this-request-error-%ED%95%B4%EA%B2%B0
public class MyFilter1 implements Filter{

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// 로드시 1회 실행
		Filter.super.init(filterConfig);
	}

	@Override
	public void destroy() {
		// 제거시 1번 실행
		Filter.super.destroy();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 전처리
        log.info("-".repeat(50));
        log.info("전처리 부분 1 : 요청을 변경한다.");
        log.info("-".repeat(50));
        log.info("RemoteAddr1 : {}", httpServletRequest.getRemoteAddr());
        //log.info("RemoteAddr2 : {}", IPAddressUtil.getIPAddress(httpServletRequest));
        log.info("RequestURI : {}", httpServletRequest.getRequestURI());
        log.info("-".repeat(50));

        chain.doFilter(request, response);
		
        // 후처리
        log.info("-".repeat(50));
        log.info("후처리 부분 1 : 응답을 변경한다.");
        log.info("-".repeat(50));
	}

}
