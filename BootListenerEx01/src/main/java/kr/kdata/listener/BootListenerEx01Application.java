package kr.kdata.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;

@ServletComponentScan
@SpringBootApplication
public class BootListenerEx01Application {

	public static void main(String[] args) {
		// SpringApplication.run(BootListenerEx01Application.class, args);
		/*
		 * 리스너 등록하기
		 */ 
		SpringApplication app = new SpringApplication(BootListenerEx01Application.class);
		app.addListeners(new MyApplicationListener());
		app.run(args);
		
	}

	// 세션 리스너 등록
	@Bean
	public ServletListenerRegistrationBean<MySessionListener> setListenerRegistrationBean1() {
	   ServletListenerRegistrationBean<MySessionListener> listenerRegBean =
	     new ServletListenerRegistrationBean<>();
	   listenerRegBean.setListener(new MySessionListener());
	   return listenerRegBean;
	}
	
	@Bean
	public ServletListenerRegistrationBean<HttpSessionListenerConfig> setListenerRegistrationBean2() {
		ServletListenerRegistrationBean<HttpSessionListenerConfig> listenerRegBean =
				new ServletListenerRegistrationBean<>();
		listenerRegBean.setListener(new HttpSessionListenerConfig());
		return listenerRegBean;
	}
}
