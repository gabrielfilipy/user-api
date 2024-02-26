package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.input.UserModelInput;
import com.br.domain.model.User;

@Component
public class UserModelMapperBack {

	@Autowired
	private ModelMapper modelMapper;
	
	public User toModel(UserModelInput userModelInput) {
		User user = 
				modelMapper.map(userModelInput, User.class);
		return user;
	}
	
}
