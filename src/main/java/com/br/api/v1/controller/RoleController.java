package com.br.api.v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.br.domain.model.Role;
import com.br.domain.service.RoleService;

@RestController
@RequestMapping("/v1/role")
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<Role>> getRoles() {
		return ResponseEntity.status(HttpStatus.OK).body(roleService.findAll());
	}
}