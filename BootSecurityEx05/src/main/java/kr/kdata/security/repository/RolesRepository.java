package kr.kdata.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.kdata.security.vo.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {

}
