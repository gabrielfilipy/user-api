package com.br.api.v1.controller;

import com.br.domain.repository.spec.TemplateSpec;
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
import org.springframework.web.bind.annotation.*;

import com.br.api.v1.model.input.UserActiveModelInput;
import com.br.api.v1.model.input.UserModelInput;
import com.br.api.v1.mapper.UserEditModelMapperBack;
import com.br.api.v1.mapper.UserModelMapper;
import com.br.api.v1.mapper.UserModelMapperBack;
import com.br.api.v1.model.*;
import com.br.domain.model.User;
import com.br.domain.service.UserService;

import io.swagger.annotations.*;

@Api(tags = "User")
@CrossOrigin(origins = "*", maxAge = 3600)
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

	@GetMapping("/listar")
	public ResponseEntity<Page<UserDepartmentModel>> getUsers(TemplateSpec.UserSpec spec,
															  @PageableDefault(page = 0, size = 5) Pageable pageable) {
	    Page<User> usersPage = userService.findAll(spec, Pageable.unpaged());
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
	    return ResponseEntity.ok().body(new PageImpl<>(userDepartmentModels, usersPage.getPageable(), usersPage.getTotalElements()));
	}

	@GetMapping("/department/{dapartmentId}")
	public ResponseEntity<List<UserModel>> getListar(@PathVariable (name = "dapartmentId") Long dapartmentId) {
		return ResponseEntity.status(HttpStatus.OK).body(userModelMapper.toCollectionModel(userService.buscarUsuariosDoDepartamento(dapartmentId)));
	}

	//@PreAuthorize("hasAnyRole('ROLE_ESTAGIARIO')")
	@GetMapping("/buscar/{id}")
	public ResponseEntity<User> getUser(@PathVariable(name = "id") Long id) {
		User user = userService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(user);
	}

	//@PreAuthorize("hasAnyRole('ROLE_ESTAGIARIO')")
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

	//@PreAuthorize("hasAnyRole('ROLE_GESTOR')")
	@PutMapping("/editar/{id}")
	public ResponseEntity<UserModel> editar(@RequestBody UserModelInput userModelInput, 
			@PathVariable(name = "id") Long id) {
		User userAtual = userService.findById(id);
		userEditModelMapperBack.copyToDomainObject(userModelInput, userAtual);
		return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.save(userAtual)));
	}

	@PatchMapping("/ativar-desativar/{id}")
    public ResponseEntity<UserModel> activateUser(@RequestBody UserActiveModelInput userActiveModelInput,
																  @PathVariable(name = "id") Long id ) {
		return ResponseEntity.status(HttpStatus.CREATED).body(
				userModelMapper.toModel(userService.activaUser(id, userActiveModelInput.isActive())));
 	}

	//@PreAuthorize("hasAnyRole('ROLE_GESTOR')")
    @PutMapping("/desativar/{id}")
    public ResponseEntity<UserModel> deactivateUser( @RequestBody UserModelInput userModelInput, 
    		@PathVariable (name = "id") Long id) {
	    User user = userService.findById(id);
		userEditModelMapperBack.copyToDomainObject(userModelInput, user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userModelMapper.toModel(userService.deactivateUser(id)));
	}
    
    @GetMapping("/filtro")
    public ResponseEntity<Page<User>> getFiltro(String matricula, String nome ,Long departmentId,
    											 @PageableDefault(page = 0, size = 10) Pageable pageable) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.Filtro(matricula, nome, departmentId, pageable));
    }
    
    @PostMapping("/novaSenha")
    public ResponseEntity<String> forgotPassword(@RequestBody UserModelInput userModelInput) {
        userService.processForgotPassword(userModelInput.getEmail());
        return ResponseEntity.ok("Nova senha enviada para o e-mail cadastrado.");
    }
}
