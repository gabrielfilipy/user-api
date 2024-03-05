package com.br.infrastructure.externalservice.rest.department;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.br.infrastructure.externalservice.rest.department.model.Department;

@Component
@FeignClient(value = "root", url = "http://localhost:8081/v1/departamento")
public interface DepartmentFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/listar")
	List<Department> getListar();
	
}
