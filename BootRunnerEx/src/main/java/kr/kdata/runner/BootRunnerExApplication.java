package kr.kdata.runner;

import java.util.Arrays;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BootRunnerExApplication implements CommandLineRunner, ApplicationRunner {

	public static void main(String[] args) {
		SpringApplication.run(BootRunnerExApplication.class, args);
	}

	/*
	CommandLineRunner
	CommandLineRunner 인터페이스는 구동 시점에 실행되는 코드가 
	자바 문자열 아규먼트 배열에 접근해야할 필요가 있는 경우에 사용합니다. 
	
	
	CommandLineRunner를 사용하는 여러가지 방법
	*/
	@Bean
	CommandLineRunner getCommandLineRunner1() {
		return (args)->{// 람다식 사용
			System.out.println("-".repeat(80));
			System.out.println("1. http://localhost:8080 으로 접속해주세요");
			System.out.println("   문서화 : http://localhost:8080/swagger-ui/index.html");
			System.out.println("-".repeat(80));
		};
	}

	@Bean
	CommandLineRunner getCommandLineRunner2() {
		return new CommandLineRunner() { // 일반 익명 클래스 사용
			@Override
			public void run(String... args) throws Exception {
				System.out.println("-".repeat(80));
				System.out.println("2. http://localhost:8080 으로 접속해주세요");
				System.out.println("-".repeat(80));
			}
		};
	}
	
	// 인터페이스 구현하여 사용
	@Override
	public void run(String... args) throws Exception {
		System.out.println("-".repeat(80));
		System.out.println("3. http://localhost:8080 으로 접속해주세요");
		System.out.println("-".repeat(80));
	}
	
	/*
	ApplicationRunner 인터페이스도 CommandLineRunner 인터페이스와 마찬가지로 
	구동 시점에 run() 메소드를 실행시키지만 다른 타입의 인자를 받습니다. 
	단순 인자의 스트링 배열을 포함한 추상화한 
	ApplicationArguments 타입의 객체가 대신 run() 메소드의 인자로 넘어옵니다.
	*/
	@Bean
	ApplicationRunner getApplicationRunner1() {
		return (args)->{ // 람다식 사용
			System.out.println("~".repeat(80));
			System.out.println("11. ApplicationRunner Args: " + Arrays.toString(args.getSourceArgs()));
			System.out.println("~".repeat(80));
		};
	}
	@Bean
	ApplicationRunner getApplicationRunner2() {
		return new ApplicationRunner() {
			// 일반 익명 클래스 사용
			@Override
			public void run(ApplicationArguments args) throws Exception {
				System.out.println("~".repeat(80));
				System.out.println("22. ApplicationRunner Args: " + Arrays.toString(args.getSourceArgs()));
				System.out.println("~".repeat(80));
				
			}
		};
	}

	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("~".repeat(80));
		System.out.println("33. ApplicationRunner Args: " + Arrays.toString(args.getSourceArgs()));
		System.out.println("~".repeat(80));
	}
}
