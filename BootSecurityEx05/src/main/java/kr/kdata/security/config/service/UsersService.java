package kr.kdata.security.config.service;

import java.util.List;

import kr.kdata.security.config.vo.Users;

public interface UsersService {
	List<Users> selectList();
	Users selectById(Long id);
	boolean insert(Users users);
	boolean update(Users users);
	boolean delete(long id);
}
