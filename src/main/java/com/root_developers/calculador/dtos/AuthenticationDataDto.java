package com.root_developers.calculador.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationDataDto {

    @NotBlank(message = "Email não pode ser null")
    @Email
    private String email;

    @NotBlank(message = " Senha não pode ser null")
    private String password;

}
