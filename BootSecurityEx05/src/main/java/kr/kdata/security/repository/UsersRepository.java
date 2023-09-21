package kr.kdata.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kdata.security.vo.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {

}
