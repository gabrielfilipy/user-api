package com.br.api.v1.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import com.br.api.v1.mapper.*;
import com.br.api.v1.model.*;
import com.br.api.v1.model.input.UserModelInput;
=======
import com.br.api.v1.mapper.UserEditModelMapperBack;
import com.br.api.v1.mapper.UserModelMapper;
import com.br.api.v1.mapper.UserModelMapperBack;
import com.br.api.v1.model.UserMatriculaModel;
import com.br.api.v1.model.UserModel;
import com.br.api.v1.model.input.UserModelInput;
import com.br.domain.exception.RegraDeNegocioException;
>>>>>>> 1f5c5d5fb8a7b1d836144fe2e4f2cb7cd68c7d70
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
	
<<<<<<< HEAD
=======
	@Autowired
	private UserEditModelMapperBack userEditModelMapperBack;
	private DepartmentFeignClient departmentFeignClient;
	
>>>>>>> 1f5c5d5fb8a7b1d836144fe2e4f2cb7cd68c7d70
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
<<<<<<< HEAD
		Optional<User> user = userService.findByMatricula(matricula);
		UserMatriculaModel userMatriculaModel = 
					userModelMapper.toModelMatricula(user.get());
		return ResponseEntity.status(HttpStatus.OK).body(userMatriculaModel);
=======
			User user = userService.findByMatricula(matricula);
			UserMatriculaModel userMatriculaModel = 
					userModelMapper.toModelMatricula(user);
			return ResponseEntity.status(HttpStatus.OK).body(userMatriculaModel);
>>>>>>> 1f5c5d5fb8a7b1d836144fe2e4f2cb7cd68c7d70
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UserModel> cadastrar(@RequestBody @Valid 
		UserModelInput userModelInput) {
		User user = userModelMapperBack.toModel(userModelInput);
		UserModel userModel = userModelMapper.toModel(userService.save(user));
		return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
	}
	@PutMapping("/editar/{id}")
	public ResponseEntity<UserModel> editar(@RequestBody UserModelInput userModelInput, 
			@PathVariable(name = "id") Long id) {
		
			User userAtual = userService.findById(id);
			userEditModelMapperBack.copyToDomainObject(userModelInput, userAtual);
			return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.save(userAtual)));
	}
	
	@PutMapping("/ativar/{id}")
    public ResponseEntity<UserModel> activateUser(@RequestBody UserModelInput userModelInput ,
    		@PathVariable (name = "id") Long id) {
		
			User user = userService.findById(id);
			userEditModelMapperBack.copyToDomainObject(userModelInput, user);
			return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.activateUser(id)));
 }
    @PutMapping("/desativar/{id}")
    public ResponseEntity<UserModel> deactivateUser( @RequestBody UserModelInput userModelInput, 
    		@PathVariable (name = "id") Long id) {
    	
	    	User user = userService.findById(id);
			userEditModelMapperBack.copyToDomainObject(userModelInput, user);
			return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.deactivateUser(id)));
	}
}
