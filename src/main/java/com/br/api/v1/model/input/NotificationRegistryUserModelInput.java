package com.br.api.v1.model.input;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NotificationRegistryUserModelInput {

    @NotBlank
    @NotNull
    private String destinatario;

    @NotBlank
    @NotNull
    private String userLogin;

    @NotBlank
    @NotNull
    private String userPassword;

    @NotBlank
    @NotNull
    private String userName;

}
