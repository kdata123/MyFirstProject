package kr.kdata.security.config.service;

import kr.kdata.security.config.vo.Users;

public interface MemberService {
	boolean joinOk(Users users);
}
