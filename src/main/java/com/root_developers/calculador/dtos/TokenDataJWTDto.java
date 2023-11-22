package com.root_developers.calculador.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TokenDataJWTDto {
    private String tokenJWT;
    public TokenDataJWTDto(String token) {
        this.tokenJWT = token;
    }

}
