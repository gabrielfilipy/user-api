package com.br.api.v1.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.api.v1.mapper.UserModelMapper;
import com.br.api.v1.mapper.UserModelMapperBack;
import com.br.api.v1.model.UserMatriculaModel;
import com.br.api.v1.model.UserModel;
import com.br.api.v1.model.input.UserModelInput;
import com.br.domain.exception.EntidadeNaoExisteException;
import com.br.domain.exception.RegraDeNegocioException;
import com.br.domain.model.User;
import com.br.domain.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

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
		try {
			Optional<User> user = userService.findByMatricula(matricula);
			UserMatriculaModel userMatriculaModel = 
					userModelMapper.toModelMatricula(user.get());
			return ResponseEntity.status(HttpStatus.OK).body(userMatriculaModel);
		} catch(Exception ex) {
			throw new EntidadeNaoExisteException("Usuario nao encontrado...");
		}
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<UserModel> cadastrar(@RequestBody @Valid UserModelInput userModelInput) {
		try {
			User user = userModelMapperBack.toModel(userModelInput);
			UserModel userModel = userModelMapper.toModel(userService.save(user));
			return ResponseEntity.status(HttpStatus.CREATED).body(userModel);
		} catch(Exception ex) {
			throw new RegraDeNegocioException(ex.getMessage(), ex);
		}
	}
	
	@PutMapping("/editar/{id}")
	public ResponseEntity<User> editar(@RequestBody User user, 
			@PathVariable(name = "id") Long id) {
		user.setId(id);
		User userNew = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userNew);
	}
	
}
