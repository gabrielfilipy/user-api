package com.br.api.v1.controller;

import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import com.br.infrastructure.externalservice.rest.department.DepartmentFeignClient;
import com.br.infrastructure.externalservice.rest.department.mapper.DepartmentModelMapper;
import com.br.infrastructure.externalservice.rest.department.model.Department;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.br.api.v1.model.input.UserModelInput;
import com.br.api.v1.mapper.*;
import com.br.api.v1.model.*;
import com.br.domain.model.User;
import com.br.domain.service.UserService;

import io.swagger.annotations.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(tags = "User")
@RestController
@RequestMapping("/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserModelMapper userModelMapper;
	
	@Autowired
	private UserModelMapperBack userModelMapperBack;
	
	@Autowired
	private UserEditModelMapperBack userEditModelMapperBack;

	@Autowired
	private DepartmentFeignClient departmentFeignClient;

	@Autowired
	private DepartmentModelMapper departmentModelMapper;
	
	@ApiOperation("Retorna uma lista de usuários.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuários listados sucesso."),
        @ApiResponse(code = 500, message = "Ocorreu um erro interno.")
    })
	
	@PreAuthorize("hasAnyRole('ROLE_ESTAGIARIO')")
	@GetMapping("/listar")
	public ResponseEntity<Page<UserDepartmentModel>> getUsers() {
	    Page<User> usersPage = userService.findAll(Pageable.unpaged());
	    List<UserDepartmentModel> userDepartmentModels = new ArrayList<>();
	    for (User user : usersPage.getContent()) {
	        UserModel userModel = userModelMapper.toModel(user);
	        Department department = departmentFeignClient.getDepartment(user.getDepartmentId());
	        DepartmentModel departmentModel = departmentModelMapper.toModel(department);

	        UserDepartmentModel userDepartmentModel = new UserDepartmentModel();
	        userDepartmentModel.setUserModel(userModel);
	        userDepartmentModel.setDepartment(departmentModel);
	        userDepartmentModels.add(userDepartmentModel);
	    }

	    Page<UserDepartmentModel> userDepartmentPage = new PageImpl<>(userDepartmentModels, usersPage.getPageable(), usersPage.getTotalElements());
	    return ResponseEntity.ok().body(userDepartmentPage);
	}


	@PreAuthorize("hasAnyRole('ROLE_ESTAGIARIO')")
	@GetMapping("/buscar/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
		User user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	@PreAuthorize("hasAnyRole('ROLE_ESTAGIARIO')")
	@GetMapping("/buscar/{matricula}/matricula")
	public ResponseEntity<UserMatriculaModel> getUser(@PathVariable(name = "matricula") String matricula) {
		User user = userService.findByMatricula(matricula);
		UserMatriculaModel userMatriculaModel = 
					userModelMapper.toModelMatricula(user);
			return ResponseEntity.status(HttpStatus.OK).body(userMatriculaModel);
	}

	//@PreAuthorize("hasAnyRole('ROLE_GESTOR')")
	@PostMapping("/cadastrar")
	public ResponseEntity<UserModel> cadastrar(@RequestBody @Valid UserModelInput userModelInput) {
		User user = userModelMapperBack.toModel(userModelInput);
		UserModel userModel = userModelMapper.toModel(userService.save(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
	}

	@PreAuthorize("hasAnyRole('ROLE_GESTOR')")
	@PutMapping("/editar/{id}")
	public ResponseEntity<UserModel> editar(@RequestBody UserModelInput userModelInput, 
			@PathVariable(name = "id") Long id) {
		User userAtual = userService.findById(id);
		userEditModelMapperBack.copyToDomainObject(userModelInput, userAtual);
		return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.save(userAtual)));
	}

	@PreAuthorize("hasAnyRole('ROLE_GESTOR')")
	@PutMapping("/ativar/{id}")
    public ResponseEntity<UserModel> activateUser(@RequestBody UserModelInput userModelInput ,
    		@PathVariable (name = "id") Long id) {
		User user = userService.findById(id);
		userEditModelMapperBack.copyToDomainObject(userModelInput, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.activateUser(id)));
	}

	@PreAuthorize("hasAnyRole('ROLE_GESTOR')")
    @PutMapping("/desativar/{id}")
    public ResponseEntity<UserModel> deactivateUser( @RequestBody UserModelInput userModelInput, 
    		@PathVariable (name = "id") Long id) {
	    User user = userService.findById(id);
		userEditModelMapperBack.copyToDomainObject(userModelInput, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.deactivateUser(id)));
	}
    
}
