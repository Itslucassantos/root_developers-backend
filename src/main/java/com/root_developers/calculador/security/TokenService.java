package com.root_developers.calculador.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.root_developers.calculador.models.IClientModel;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Base64;
import java.util.HashMap;

@Service
public class TokenService {

    public String generateToken(Object principal) {
        try {
            //"secret" é a senha passsada para poder gerar token.
            //.withClaim(), vc pode adicionar qualquer informação que queira.
            var algoritmo = Algorithm.HMAC256("project12");
            IClientModel client = (IClientModel) principal;
            return JWT.create()
                    .withIssuer("calculador")
                    .withSubject(client.getEmail())
                    //tempo de duração que o token será válido.
                    .withExpiresAt(expirationDate())
                    .sign(algoritmo);
        } catch (JWTCreationException exception){
            throw new RuntimeException("erro ao gerrar token jwt", exception);
        }
    }


    // olha se o token é válido e devolve o subject que está armazenado dentro do tokenJWT.
    public String getSubject(String tokenJWT){
        //faz a divisão do token "split" pelo ponto, com isso, a gente obtem um array de String.
        String[] chunks = tokenJWT.split("\\.");
        //Base64 é o tipo de criptografia do token.
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        try {
            //HashMap.class é quem faz toda a decodificação para um mapa.
            HashMap payloadMap = new ObjectMapper().readValue(payload, HashMap.class);
            if(payloadMap.get("iss").equals("calculador")) {
                return (String) payloadMap.get("sub");
            } else {
                throw new RuntimeException("Token JWT inválido ou expirado!");
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private Instant expirationDate() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

}
