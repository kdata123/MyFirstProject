package kr.kdata.security.config;

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

@Configuration
@EnableWebSecurity
public class BasicConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/", "/home","/index","/today","/hello").permitAll()
				.requestMatchers("/images/**", "/css/**","/html/**","/js/**","/webjars/**","/axicon/**").permitAll()
				.requestMatchers("/swagger-ui/**", "/v3/**").permitAll()
				.requestMatchers("/member").hasAnyRole("ADMIN","USER")
				.requestMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated()
			);
		
		http
			.exceptionHandling((exception)->{
				// 접근불가 페이지 지정
				exception.accessDeniedPage("/accessDenied");
			});
		
		http
			.formLogin((form) ->{
				// 사용자 로그인 페이지 지정
				form.loginPage("/login");
				// 로그인 권한 설정
				form.permitAll();
			});
		
		http
			.logout((logout) ->{
				// 로그아웃 권한 설정
				logout.permitAll();	
			});
		return http.build();
	}
	
    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {
    	//System.out.println(passwordEncoder.encode("123456"));
    	UserDetails user = User.withUsername("user")
    			.password(passwordEncoder.encode("123456"))
    			.roles("USER")
    			.build();
    	
    	//System.out.println(passwordEncoder.encode("123456"));
    	UserDetails admin = User.withUsername("admin")
    			.password(passwordEncoder.encode("123456"))
    			.roles("USER", "ADMIN")
    			.build();
    	return new InMemoryUserDetailsManager(user, admin);
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return encoder;
    }
}