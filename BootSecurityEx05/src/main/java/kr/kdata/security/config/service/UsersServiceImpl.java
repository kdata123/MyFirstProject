package kr.kdata.security.config.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.kdata.security.config.repository.UsersRepository;
import kr.kdata.security.config.vo.Users;
import lombok.extern.slf4j.Slf4j;

@Service("usersService")
@Slf4j
public class UsersServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<Users> selectList() {
		log.info("selectList() 호출!!!");
		List<Users> list = null;
		try{
			list = usersRepository.findAll();
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectList() 리턴 : {}", list);
		return list;
	}

	@Override
	public Users selectById(Long id) {
		log.info("selectById({}) 호출!!!", id);
		Users users = null;
		try {
			Optional<Users> optional = usersRepository.findById(id);
			if(optional.isPresent()) {
				users =  optional.get();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("selectById({}) 리턴 : {}", id, users);
		return users;
	}

	@Override
	public boolean insert(Users users) {
		log.info("insert({}) 호출!!!", users);
		boolean result = false;
		try {
			if(users!=null) {
				usersRepository.save(users);
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("insert({}) 리턴 : {}", users, result);
		return result;
	}

	@Override
	public boolean update(Users users) {
		log.info("update({}) 호출!!!", users);
		boolean result = false;
		try {
			Optional<Users> optional = usersRepository.findById(users.getId());
			if(optional.isPresent()) {
				usersRepository.save(users);
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("update({}) 리턴 : {}", users, result);
		return result;
	}

	@Override
	public boolean delete(long id) {
		log.info("delete({}) 호출!!!", id);
		boolean result = false;
		try {
			Optional<Users> optional = usersRepository.findById(id);
			if(optional.isPresent()) {
				usersRepository.deleteById(id);
				result = true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		log.info("delete({}) 리턴 : {}", id, result);
		return result;
	}
	
}
