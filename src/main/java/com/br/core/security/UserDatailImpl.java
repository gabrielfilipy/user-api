package com.br.core.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.br.domain.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDatailImpl implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Boolean active;
	
	private String nome;
	
	private String password;
	
	private String matricula;

	private String email;

	private Long departmentId;
	
	private Collection<? extends GrantedAuthority> authorities; 
	
	public static UserDatailImpl build (User user) {
		List<GrantedAuthority> authorities = user.getRoles().stream()
				.map(role -> new SimpleGrantedAuthority(role.getAuthority()))
				.collect(Collectors.toList());
		return new UserDatailImpl(
			user.getId(),
			user.getActive(),
			user.getNome(),
			user.getPassword(),
			user.getMatricula(),
			user.getEmail(),
			user.getDepartmentId(),
			authorities	
		);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.getAuthorities();
	}
	
	@Override
	public String getPassword()  {
		return this.password; 
	}

	@Override
	public String getUsername() { 
		return this.matricula;
	}
	
	@Override
	public boolean isAccountNonExpired() { return true; }

	@Override
	public boolean isAccountNonLocked()  { return true; }
		
	@Override
	public boolean isCredentialsNonExpired()  { return true; }

	@Override
	public boolean isEnabled()  { return true; }
}

