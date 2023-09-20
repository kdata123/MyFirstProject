package kr.kdata.security.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kdata.security.config.vo.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
