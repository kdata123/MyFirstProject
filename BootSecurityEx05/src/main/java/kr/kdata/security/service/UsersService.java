package kr.kdata.security.service;

import java.util.List;

import kr.kdata.security.vo.Users;

public interface UsersService {
	List<Users> selectList();
	Users selectById(Long id);
	boolean insert(Users users);
	boolean update(Users users);
	boolean delete(long id);
}
