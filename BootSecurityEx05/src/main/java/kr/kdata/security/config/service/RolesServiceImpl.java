package kr.kdata.security.config.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.security.config.repository.RolesRepository;
import kr.kdata.security.config.vo.Roles;
import lombok.extern.slf4j.Slf4j;

@Service("rolesService")
@Slf4j
public class RolesServiceImpl implements RolesService{

	@Autowired
	private RolesRepository rolesRepository;

	@Override
	public List<Roles> selectList() {
		log.info("selectList() 호출!!!");
		List<Roles> list = null;
		try{
			list = rolesRepository.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectList() 리턴 : {}", list);
		return list;
	}

	@Override
	public Roles selectById(Long id) {
		log.info("selectById({}) 호출!!!", id);
		Roles roles = null;
		try {
			Optional<Roles> optional = rolesRepository.findById(id);
			if(optional.isPresent()) {
				roles =  optional.get();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectById({}) 리턴 : {}", id, roles);
		return roles;
	}

	@Override
	public boolean insert(Roles roles) {
		log.info("insert({}) 호출!!!", roles);
		boolean result = false;
		try {
			if(roles!=null) {
				rolesRepository.save(roles);
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("insert({}) 리턴 : {}", roles, result);
		return result;
	}

	@Override
	public boolean update(Roles roles) {
		log.info("update({}) 호출!!!", roles);
		boolean result = false;
		try {
			Optional<Roles> optional = rolesRepository.findById(roles.getId());
			if(optional.isPresent()) {
				rolesRepository.save(roles);
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("update({}) 리턴 : {}", roles, result);
		return result;
	}

	@Override
	public boolean delete(long id) {
		log.info("delete({}) 호출!!!", id);
		boolean result = false;
		try {
			Optional<Roles> optional = rolesRepository.findById(id);
			if(optional.isPresent()) {
				rolesRepository.deleteById(id);
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("delete({}) 리턴 : {}", id, result);
		return result;
	}
	
}
