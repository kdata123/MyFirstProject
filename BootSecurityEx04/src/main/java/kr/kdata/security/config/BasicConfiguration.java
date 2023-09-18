package kr.kdata.security.config;

import java.util.stream.Stream;

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
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.security.web.util.matcher.IpAddressMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

	// 누구나 접근 가능한 URL들 등록
	private static final String[] WHITE_LIST = { "/", "/index", "/home", "/today", "/hello", "/html/**", "/css/**",
			"/images/**", "/css/**", "/axicon/**", "/swagger-ui/**", "/v3/**", "/webjars/**" , "/h2/**"};

	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http, HandlerMappingIntrospector introspector) throws Exception {
		MvcRequestMatcher.Builder mvc = new MvcRequestMatcher.Builder(introspector);
		// h2-console 허용
		// http.csrf().disable();
		http.csrf((csrf) -> {
			csrf.ignoringRequestMatchers(mvc.pattern("/h2/**"));
			//csrf.disable();
		})
		// spring security 적용 후 Refused to display in a frame because it set
		// 'X-Frame-Options' to 'DENY' 발생 처리
		// http.headers().frameOptions().sameOrigin();
		.headers((header) -> {
			header.frameOptions((frameOptions) -> {
				frameOptions.sameOrigin();
				// frameOptions.disable();
			});
		})
		.authorizeHttpRequests((authorize) -> {
			try {
				// for(String WHITE : WHITE_LIST)  authorize.requestMatchers(mvc.pattern(WHITE)).permitAll();
				// 스트림을 이용하여 처리
				authorize.requestMatchers(Stream.of(WHITE_LIST).map(mvc::pattern).toArray(RequestMatcher[]::new)).permitAll();
				
				authorize.requestMatchers(PathRequest.toH2Console()).permitAll();
				authorize.requestMatchers(new IpAddressMatcher("127.0.0.1")).permitAll();
				authorize.requestMatchers(mvc.pattern("/member")).hasAnyRole("USER","ADMIN");
				authorize.requestMatchers(mvc.pattern("/admin")).hasRole("ADMIN");
				authorize.anyRequest().authenticated(); // 나머지는 모두 인증해야 함

			} catch (Exception e) {
				e.printStackTrace();
			}
		})
		.exceptionHandling((exception) -> {
			// 접근불가 페이지 지정
			exception.accessDeniedPage("/accessDenied");
		})
		.formLogin((form) -> {
			// 사용자 로그인 페이지 지정
			form.loginPage("/login");
			form.defaultSuccessUrl("/");
			// 로그인 권한 설정
			form.permitAll();
		})
		.logout((logout) -> {
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
		UserDetails admin = User.withUsername("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN").build();
		return new InMemoryUserDetailsManager(user, admin);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return encoder;
	}

}