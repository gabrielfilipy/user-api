package com.br.infrastructure.externalservice.rest.department;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import com.br.infrastructure.externalservice.rest.department.model.Department;

@Component
@FeignClient(value = "root", url = "http://localhost:8081/v1/department")
public interface DepartmentFeignClient {

	@RequestMapping(method = RequestMethod.GET, value = "/buscar/{id}")
	Department getDepartment(@PathVariable("id") Long id);
	
}
