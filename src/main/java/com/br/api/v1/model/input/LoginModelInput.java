package com.br.api.v1.model.input;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginModelInput {

    @NotBlank
    private String matricula;

    @NotBlank
    private String password;

}
