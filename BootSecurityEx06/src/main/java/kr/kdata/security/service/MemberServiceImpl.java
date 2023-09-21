package kr.kdata.security.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.kdata.security.repository.RolesRepository;
import kr.kdata.security.repository.UsersRepository;
import kr.kdata.security.vo.Roles;
import kr.kdata.security.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Service("memberService")
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	private UsersRepository usersRepository;

	@Autowired
	private RolesRepository rolesRepository;
	
	
	@Override
	public boolean joinOk(Users users) {
		log.info("joinOk({}) 호출!!!", users);
		boolean result = false;
		try {
			PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
			if(users!=null) {
				// 회원 정보 암호화해서 저장
				users.setPassword(passwordEncoder.encode(users.getPassword()));
				users.setEnabled(true);
				usersRepository.save(users);
				// 권한 저장
				Roles roles = Roles.builder().role("ROLE_USER").username(users.getUsername()).build();
				rolesRepository.save(roles);

				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("insert({}) 리턴 : {}", users, result);
		return result;
	}

	// 회원 정보를 얻는 메서드
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = null;
		Optional<Users> optional = usersRepository.findByUsername(username);
		if(optional.isPresent()) users = optional.get();
		return users;
	}
}
