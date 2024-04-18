package com.br.infrastructure.externalservice.rest.department.mapper;

import com.br.api.v1.model.DepartmentModel;
import com.br.infrastructure.externalservice.rest.department.model.Department;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepartmentModelMapper {
	
	@Autowired
	private ModelMapper modelMapper;
	
	public DepartmentModel toModel(Department department) {
		return modelMapper.map(department, DepartmentModel.class);
	}

}
