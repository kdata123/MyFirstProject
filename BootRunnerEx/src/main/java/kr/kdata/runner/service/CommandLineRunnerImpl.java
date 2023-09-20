package kr.kdata.runner.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

// 별도의 클래스를 만들고 @Component로 등록하면 
// 컴포넌트 스캔이되고 구동 시점에 run 메소드의 코드가 실행됩니다.
@Component
public class CommandLineRunnerImpl implements CommandLineRunner {
	@Override
	public void run(String... args) throws Exception {
		System.out.println("=".repeat(80));
		System.out.println("4. http://localhost:8080 으로 접속해주세요");
		System.out.println("-".repeat(80));
		System.out.println(args.length + "개의 인수가 넘어옴");
		System.out.println("-".repeat(80));
		int count = 0;
		for(String arg : args) {
			System.out.println(String.format("%04d. %s", ++count, arg));
		}
		System.out.println("=".repeat(80));
	}
}
