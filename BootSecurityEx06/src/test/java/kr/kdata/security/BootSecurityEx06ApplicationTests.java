package kr.kdata.security;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.kdata.security.vo.Roles;
import kr.kdata.security.vo.Users;

@SpringBootTest
class BootSecurityEx06ApplicationTests {

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Test
	void contextLoads() {
		Users users = Users.builder().username("admin").enabled(true).password("123456").build();
		System.out.println(users);
		users.setPassword(passwordEncoder.encode("123456"));
		System.out.println(users);
		Roles roles1 = Roles.builder().username("admin").role("ROLE_ADMIN").build();
		System.out.println(roles1);
		Roles roles2 = Roles.builder().username("admin").role("ROLE_USER").build();
		System.out.println(roles2);
	}

}
