package kr.kdata.security.config.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kdata.security.config.vo.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

}
