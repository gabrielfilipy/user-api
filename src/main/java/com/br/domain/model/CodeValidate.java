 package com.br.domain.model;

import java.io.Serializable;

import javax.persistence.*;

import lombok.*;

@Getter
@Setter
@Table(name = "TBL_CODE_VALIDATE")
@Entity
public class CodeValidate implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "code")
	private String code;
	
	@OneToOne
	private User user;
	
}
