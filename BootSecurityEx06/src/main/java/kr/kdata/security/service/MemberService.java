package kr.kdata.security.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import kr.kdata.security.vo.Users;

public interface MemberService extends UserDetailsService{
	boolean joinOk(Users users);
}
