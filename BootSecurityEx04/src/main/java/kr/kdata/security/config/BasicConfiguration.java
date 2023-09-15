package kr.kdata.security.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.IpAddressMatcher;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

	// 누구나 접근 가능한 URL들 등록
	private static final String[] WHITE_LIST = { "/", "/index", "/home", "/today", "/hello", "/html/**", "/css/**",
			"/images/**", "/css/**", "/axicon/**", "/swagger-ui/**", "/v3/**", "/webjars/**", "/h2/**" };

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		// h2-console 허용
		// http.csrf().disable();
		http.csrf((csrf) -> {
			csrf.ignoringRequestMatchers("/h2/**");
			//csrf.disable();
		});
		// spring security 적용 후 Refused to display in a frame because it set
		// 'X-Frame-Options' to 'DENY' 발생 처리
		// http.headers().frameOptions().sameOrigin();
		http.headers((header) -> {
			header.frameOptions((frameOptions) -> {
				frameOptions.sameOrigin();
			});
		});

		http.authorizeHttpRequests((authorize) -> {
			try {
				authorize.requestMatchers("/member").hasAnyRole("ADMIN", "USER");
				authorize.requestMatchers("/admin").hasRole("ADMIN");
				authorize.requestMatchers(WHITE_LIST).permitAll();

				authorize.requestMatchers(PathRequest.toH2Console()).permitAll();
				authorize.requestMatchers(new IpAddressMatcher("127.0.0.1")).permitAll();
				authorize.anyRequest().authenticated(); // 나머지는 모두 인증해야 함

			} catch (Exception e) {
				e.printStackTrace();
			}
		});

		http.exceptionHandling((exception) -> {
			// 접근불가 페이지 지정
			exception.accessDeniedPage("/accessDenied");
		});

		http.formLogin((form) -> {
			// 사용자 로그인 페이지 지정
			form.loginPage("/login");
			// 로그인 권한 설정
			form.permitAll();
		});

		http.logout((logout) -> {
			// 로그아웃 권한 설정
			logout.permitAll();
		});
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
		// System.out.println(passwordEncoder.encode("123456"));
		UserDetails user = User.withUsername("user").password(passwordEncoder.encode("123456")).roles("USER").build();

		// System.out.println(passwordEncoder.encode("123456"));
		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}
}