package kr.kdata.runner.service;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

// 별도의 클래스를 만들고 @Component로 등록하면 
// 컴포넌트 스캔이되고 구동 시점에 run 메소드의 코드가 실행됩니다.
@Component
public class ApplicationRunnerImpl implements ApplicationRunner {
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("=".repeat(80));
		System.out.println("44. http://localhost:8080 으로 접속해주세요");
		System.out.println("~".repeat(80));
		System.out.println(args.getSourceArgs().length + "개의 인수가 넘어옴");
		System.out.println("~".repeat(80));
		int count = 0;
		for(String arg : args.getSourceArgs()) {
			System.out.println(String.format("%04d. %s", ++count, arg));
		}
		System.out.println("=".repeat(80));
	}
}
