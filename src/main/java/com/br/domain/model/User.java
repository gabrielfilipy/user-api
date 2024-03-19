package com.br.domain.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
@Table(name = "TBL_USER")
@Entity
public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
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
	private Long departmentId;

}
