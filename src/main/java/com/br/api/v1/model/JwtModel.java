package com.br.api.v1.model;

import lombok.*;

@RequiredArgsConstructor
@Getter
@Setter
public class JwtModel {

    @NonNull
    private String token;
    private String type = "Bearer";

}
