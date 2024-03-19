package com.br.api.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.br.api.v1.mapper.*;
import com.br.api.v1.model.*;
import com.br.api.v1.model.input.UserModelInput;
import com.br.domain.model.User;
import com.br.domain.service.UserService;
import com.br.infrastructure.externalservice.rest.department.DepartmentFeignClient;
import com.br.infrastructure.externalservice.rest.department.model.Department;

import io.swagger.annotations.*;

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
	
	@ApiOperation("Retorna uma lista de usuários.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuários listados sucesso."),
        @ApiResponse(code = 500, message = "Ocorreu um erro interno.")
    })
	@GetMapping("/listar")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
	}
	
	@GetMapping("/buscar/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
		User user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}
	
	@GetMapping("/buscar/{matricula}/matricula")
	public ResponseEntity<UserMatriculaModel> getUser(@PathVariable(name = "matricula") String matricula) {
		Optional<User> user = userService.findByMatricula(matricula);
		UserMatriculaModel userMatriculaModel = 
					userModelMapper.toModelMatricula(user.get());
		return ResponseEntity.status(HttpStatus.OK).body(userMatriculaModel);
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UserModel> cadastrar(@RequestBody @Valid 
		UserModelInput userModelInput) {
		User user = userModelMapperBack.toModel(userModelInput);
		UserModel userModel = userModelMapper.toModel(userService.save(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<User> editar(@RequestBody User user, 
			@PathVariable(name = "id") Long id) {
		user.setId(id);
		User userNew = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userNew);
	}
	
}
