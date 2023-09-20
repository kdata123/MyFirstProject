package kr.kdata.security.config.vo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
create table users (
    username varchar(50) not null primary key,
    password varchar(120) not null,
    enabled boolean not null
);
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Users {
	@Id @GeneratedValue 
	private Long id;
	private String username;
	private String password;
	private boolean enabled;

}
