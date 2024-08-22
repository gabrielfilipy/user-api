package com.br.api.v1.model;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentModel {

    private UUID departamentoId;
    private String nome;

}
