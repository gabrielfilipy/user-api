package com.br.api.v1.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDepartmentModel {

	private UserModel userModel;
	private DepartmentModel department;
	
}
