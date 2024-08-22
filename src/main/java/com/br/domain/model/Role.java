package com.br.domain.model;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import com.br.domain.enums.RoleType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

@Getter
@Setter
@ToString
@Entity
@Table(name = "TBL_ROLES")
public class Role implements Serializable, GrantedAuthority {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private UUID roleId;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false, unique = true, length = 30)
	private RoleType roleType;

	@Override
	@JsonIgnore
	public String getAuthority() {
		return this.roleType.toString();
	}
	

}
