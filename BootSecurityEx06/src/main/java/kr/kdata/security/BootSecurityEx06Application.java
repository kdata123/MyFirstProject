package kr.kdata.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.kdata.security.repository.RolesRepository;
import kr.kdata.security.repository.UsersRepository;
import kr.kdata.security.vo.Roles;
import kr.kdata.security.vo.Users;

@SpringBootApplication
public class BootSecurityEx06Application {
	public static void main(String[] args) {
		SpringApplication.run(BootSecurityEx06Application.class, args);
	}

	// @Autowired
	// private JdbcTemplate jdbcTemplate;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired // 자동으로 주입
	private UsersRepository usersRepository;
	@Autowired // 자동으로 주입
	private RolesRepository rolesRepository;

	@Bean
	CommandLineRunner getCommandLineRunner() {
		return (args)->{
			/*
			jdbcTemplate.update("insert into USERS values(?,?,?)", new Object[] {"admin", passwordEncoder.encode("123456"), true});
			jdbcTemplate.update("insert into USERS values(?,?,?)", new Object[] {"user", passwordEncoder.encode("123456"), true});
			jdbcTemplate.update("insert into ROLES values(?,?)", new Object[] {"admin", "ROLE_ADMIN"});
			jdbcTemplate.update("insert into ROLES values(?,?)", new Object[] {"admin", "ROLE_USER"});
			jdbcTemplate.update("insert into ROLES values(?,?)", new Object[] {"user", "ROLE_USER"});
			*/
			Users users1 = Users.builder().enabled(true).password(passwordEncoder.encode("123456")).username("admin")
						   .email("kkk@kdata.org")
						   .phone("010-2222-3333")
						   .build();
			Users users2 = Users.builder().enabled(true).password(passwordEncoder.encode("123456")).username("user")
						   .email("gggg@kdata.org")
						   .phone("010-5555-1111")
						   .build();
			usersRepository.saveAll(Arrays.asList(users1,users2));
			
			Roles roles1 = Roles.builder().role("ROLE_ADMIN").username("admin").build(); 
			Roles roles2 = Roles.builder().role("ROLE_USER").username("admin").build(); 
			Roles roles3 = Roles.builder().role("ROLE_USER").username("user").build();
			rolesRepository.saveAll(Arrays.asList(roles1,roles2,roles3));
		};
	}
}
