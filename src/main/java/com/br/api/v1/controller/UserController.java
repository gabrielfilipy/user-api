package com.br.api.v1.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.br.api.v1.model.input.UserModelInput;
import com.br.api.v1.mapper.*;
import com.br.api.v1.model.*;
import com.br.domain.model.User;
import com.br.domain.service.UserService;

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
	
	@Autowired
	private UserEditModelMapperBack userEditModelMapperBack;
	
	@ApiOperation("Retorna uma lista de usuários.")
    @ApiResponses({
        @ApiResponse(code = 200, message = "Usuários listados sucesso."),
        @ApiResponse(code = 500, message = "Ocorreu um erro interno.")
    })
	@PreAuthorize("hasAnyRole('ROLE_ESTAGIARIO')")
	@GetMapping("/listar")
	public ResponseEntity<List<User>> getUsers() {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findAll());
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

	@PreAuthorize("hasAnyRole('ROLE_GESTOR')")
	@PostMapping("/cadastrar")
	public ResponseEntity<UserModel> cadastrar(@RequestBody @Valid 
		UserModelInput userModelInput) {
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
