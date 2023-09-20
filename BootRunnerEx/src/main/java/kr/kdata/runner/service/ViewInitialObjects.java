package kr.kdata.runner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ViewInitialObjects implements CommandLineRunner{

	@Autowired
	private ApplicationContext applicationContext;
	
	@Override
	public void run(String... args) throws Exception {
		System.out.println("=^=".repeat(30));
		System.out.println("초기 자동으로 등록되는 객체들...");
		System.out.println(applicationContext.getBeanDefinitionCount() + "개");
		System.out.println("-".repeat(80));
		int count=0;
		for(String beanName : applicationContext.getBeanDefinitionNames()) {
			System.out.println(String.format("%04d. %s", ++count, beanName));
		}
		System.out.println("=^=".repeat(30));
		
	}

}
