package kr.kdata.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @WebFilter(urlPatterns = "/main/*") // 이것만 쓰면 안됨
public class MyFilter2 implements Filter{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        //HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 전처리
        log.info("-".repeat(50));
        log.info("전처리 부분 2 : 요청을 변경한다.");
        log.info("-".repeat(50));
        log.info("RemoteAddr1 : {}", httpServletRequest.getRemoteAddr());
        //log.info("RemoteAddr2 : {}", IPAddressUtil.getIPAddress(httpServletRequest));
        log.info("RequestURI : {}", httpServletRequest.getRequestURI());
        log.info("-".repeat(50));

        chain.doFilter(request, response);
		
        // 후처리
        log.info("-".repeat(50));
        log.info("후처리 부분 2 : 응답을 변경한다.");
        log.info("-".repeat(50));
	}

}
