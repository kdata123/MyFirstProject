package kr.kdata.security.vo;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Builder
public class Users implements UserDetails{
	@Id @GeneratedValue 
	private Long id;
	private String username;
	private String password;
	private String email;
	private String phone;
	private boolean enabled;
	
	@Override // 권한 반환
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority("user"));
	}
	@Override // 계정 만료여부 반환
	public boolean isAccountNonExpired() {
		// true : 만료되지 않음, false : 만료됨
		return true;
	}
	@Override// 계정 잠김여부 반환
	public boolean isAccountNonLocked() {
		// true : 잠김되지 않음, false : 잠김
		return true;
	}
	@Override // 암호 만료여부 반환 
	public boolean isCredentialsNonExpired() {
		// true : 만료되지 않음, false : 만료됨
		return true;
	}

}
