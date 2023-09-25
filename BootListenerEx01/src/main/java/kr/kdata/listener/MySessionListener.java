package kr.kdata.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;

@WebListener
public class MySessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		HttpSessionListener.super.sessionCreated(se);
		System.out.println("==============================");
		System.out.println("새로운 세션이 시작되었습니다.");
		System.out.println("==============================");
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		System.out.println("==============================");
		System.out.println("세션이 종료되었습니다.");
		System.out.println("==============================");
		HttpSessionListener.super.sessionDestroyed(se);
	}

}
