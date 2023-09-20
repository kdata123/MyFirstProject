package kr.kdata.security.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import kr.kdata.security.config.vo.Roles;
import kr.kdata.security.config.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Service("memberService")
@Slf4j
public class MemberServiceImpl implements MemberService {
	@Autowired
	UsersService usersService;
	
	@Autowired
	RolesService rolesService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public boolean joinOk(Users users) {
		log.info("joinOk({}) 호출!!!", users);
		boolean result = false;
		try {
			if(users!=null) {
				// 회원 정보 암호화해서 저장
				users.setPassword(passwordEncoder.encode(users.getPassword()));
				users.setEnabled(true);
				usersService.insert(users);
				// 권한 저장
				Roles roles = Roles.builder().role("ROLE_USER").username(users.getUsername()).build();
				rolesService.insert(roles);

				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("insert({}) 리턴 : {}", users, result);
		return result;
	}
}
