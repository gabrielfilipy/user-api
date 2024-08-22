package com.br.core.security;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.domain.model.User;
import com.br.domain.repository.UserRepository;

@Service
public class UserDatailServiceImpl implements UserDetailsService{
	 
	@Autowired
	private UserRepository userRepository;
	
	public UserDetails loadUserById(UUID userId) throws AuthenticationCredentialsNotFoundException {
		User userModel = userRepository.findById(userId)
				.orElseThrow(() -> 
				new AuthenticationCredentialsNotFoundException("Usuario Not Found with userId :" + userId));
        return UserDatailImpl.build(userModel);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		User user = userRepository.findByMatricula(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User not found white name: " + userName));
		return UserDatailImpl.build(user);
	}

}
