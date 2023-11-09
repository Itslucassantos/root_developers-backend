package com.root_developers.calculador.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
// Para dizer que vamos personalizar as classes de segurança
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    private SecurityFilter securityFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeHttpRequests().requestMatchers(HttpMethod.POST, "/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/client/legalClient").permitAll()
                .requestMatchers(HttpMethod.POST, "/client/physicalClient").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/client/{clientId}").permitAll()
                .requestMatchers(HttpMethod.PUT, "/client").permitAll()
                .requestMatchers(HttpMethod.GET, "/client/{clientId}").permitAll()
                .anyRequest().authenticated()
                .and().addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    // serve para exportar uma classe para o Spring, assim ele consegue fazer a injeção de dependência de outras classes.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        //cria um objeto authenticationManager pq o Spring não sabe fazer isso automaticamente, apesar de a classer ser dele.
        return configuration.getAuthenticationManager();
    }

    // Cria as senhas criptografadas.
    @Bean
    //PasswordEncoder representa a classe de algoritmo de senhas.
    public PasswordEncoder passwordEncoder(){
        //com isso, ensina ao Spring que é pra usar esse hash de senha, esse tipo de criptografia.
        return new BCryptPasswordEncoder();
    }

}
