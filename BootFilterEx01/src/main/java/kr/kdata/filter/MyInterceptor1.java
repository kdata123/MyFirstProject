package kr.kdata.filter;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MyInterceptor1 implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 컨트롤러에 진입하기 전에 실행됩니다. 반환 값이 true일 경우 컨트롤러로 진입하고 false일 경우 진입하지 않습니다.
		// Object handler는 진입하려는 컨트롤러의 클래스 객체가 담겨있습니다.
		log.info("~".repeat(50));
		log.info("MyInterceptor1 preHandle 호출");
		log.info("~".repeat(50));
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 컨트롤러 진입 후 View가 랜더링 되기 전에 수행됩니다.
		log.info("~".repeat(50));
		log.info("MyInterceptor1 postHandle 호출");
		log.info("~".repeat(50));
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// 컨트롤러 진입 후 view가 랜더링 된 후에 실행되는 메소드입니다.
		log.info("~".repeat(50));
		log.info("MyInterceptor1 afterCompletion 호출");
		log.info("~".repeat(50));
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}
