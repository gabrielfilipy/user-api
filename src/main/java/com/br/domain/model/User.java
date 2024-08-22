package com.br.domain.model;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Table(name = "TBL_USER")
@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private UUID userId;
	
	private Boolean active;
	
	@NotBlank(message = "Nao pode ficar em branco")
	@NotNull
	@Column(name = "nome")
	private String nome;
	
	@JsonIgnore
	@Column(name = "password")
	private String password;
	
	@Column(name = "matricula")
	private String matricula;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "department_id")
	private UUID departmentId;

	@Column(name = "telefone")
	private String telefone;

	@Column(name = "endereco")
	private String endereco;

	@Column(name = "rg")
	private String rg;

	@Column(name = "cpf")
	private String cpf;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "TBL_USERS_ROLES",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();

}
