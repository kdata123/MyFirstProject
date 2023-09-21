package kr.kdata.security.service;

import java.util.List;

import kr.kdata.security.vo.Roles;

public interface RolesService {
	List<Roles> selectList();
	Roles selectById(Long id);
	boolean insert(Roles roles);
	boolean update(Roles roles);
	boolean delete(long id);
}
