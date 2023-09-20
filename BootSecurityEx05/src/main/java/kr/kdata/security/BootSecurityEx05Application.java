package kr.kdata.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import kr.kdata.security.config.repository.RolesRepository;
import kr.kdata.security.config.repository.UsersRepository;
import kr.kdata.security.config.vo.Roles;
import kr.kdata.security.config.vo.Users;

@SpringBootApplication
public class BootSecurityEx05Application {
	public static void main(String[] args) {
		SpringApplication.run(BootSecurityEx05Application.class, args);
	}

	//@Autowired
	//private JdbcTemplate jdbcTemplate;
	
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
			Users users1 = Users.builder().enabled(true).password(passwordEncoder.encode("123456")).username("admin").build();
			Users users2 = Users.builder().enabled(true).password(passwordEncoder.encode("123456")).username("user").build();
			/*
			System.out.println(users1);
			System.out.println(users2);
			*/
			usersRepository.save(users1);
			usersRepository.save(users2);
			
			Roles roles1 = Roles.builder().role("ROLE_ADMIN").username("admin").build(); 
			Roles roles2 = Roles.builder().role("ROLE_USER").username("admin").build(); 
			Roles roles3 = Roles.builder().role("ROLE_USER").username("user").build();
			/*
			System.out.println(roles1);
			System.out.println(roles2);
			System.out.println(roles3);
			*/
			rolesRepository.save(roles1);
			rolesRepository.save(roles2);
			rolesRepository.save(roles3);
		};
	}
}
