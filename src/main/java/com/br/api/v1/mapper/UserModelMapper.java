package com.br.api.v1.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.br.api.v1.model.UserMatriculaModel;
import com.br.api.v1.model.UserModel;
import com.br.domain.model.User;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserModelMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	public UserMatriculaModel toModelMatricula(User user) {
		UserMatriculaModel userMatriculaModel = 
				modelMapper.map(user, UserMatriculaModel.class);
		return userMatriculaModel;
	}
	
	public UserModel toModel(User user) {
		UserModel userMatriculaModel = 
				modelMapper.map(user, UserModel.class);
		return userMatriculaModel;
	}

	public List<UserModel> toCollectionModel(List<User> users) {
		return users.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
}
