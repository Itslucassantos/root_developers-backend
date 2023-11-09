package com.root_developers.calculador.controller;

import com.root_developers.calculador.dtos.AuthenticationDataDto;
import com.root_developers.calculador.dtos.TokenDataJWTDto;
import com.root_developers.calculador.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {
    //para poder validar o login, como estamos usando uma implementação no AutenticacaoService, ele está fznd a validação
    // por baixo dos banos, ent é preciso chamar a classe AuthenticationManager para fazer a conexão/validação.
    @Autowired
    private AuthenticationManager manager;

    //Fznd a injeção de dependências para poder usar a classe chamada.
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid AuthenticationDataDto dada) {
        //convertendo do nosso DTO para o DTO do UsernamePasswordAuthenticationToken
        var authenticationToken = new UsernamePasswordAuthenticationToken(dada.getEmail(), dada.getPassword());
        // Passando nosso DTO convertido, ele devolve um objeto que representa um usuário autenticado no sistema.
        var authentication = manager.authenticate(authenticationToken);
        //getPrincipal() é para pegar o usuário logado.
        var tokenJWT = tokenService.generateToken(authentication.getPrincipal());

        return ResponseEntity.ok(new TokenDataJWTDto(tokenJWT));
    }

}
