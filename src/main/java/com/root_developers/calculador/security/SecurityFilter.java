package com.root_developers.calculador.security;

import com.root_developers.calculador.repositories.LegalClientRepository;
import com.root_developers.calculador.repositories.PhysicalClientRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private LegalClientRepository legalClientRepository;

    @Autowired
    private PhysicalClientRepository physicalClientRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        var tokenJWT = recoverToken(request);
        //validando o token e mostrando o subject, se tiver o cabeçalho. pq agr o Spring que vê
        //quem está logado ou nn, como configurado no securityConfigurations.
        if(tokenJWT != null){
            var subject = tokenService.getSubject(tokenJWT);
            //considere que essa pessoa está autenticada, eu garanto isso. Feito uma autenticação forçada para o Spring entender.
            //passa o subject, pq é onde está guardado o email do client.
            var client = this.legalClientRepository.findByEmail(subject);
            if(client == null) {
                client = this.physicalClientRepository.findByEmail(subject);
            }
            var authentication = new UsernamePasswordAuthenticationToken(client, null,
                    client.getAuthorities());
            // força que o usuário está logado
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        //para continuar o fluxo da requisição, é necessário para chamar os próximos filtros da aplicação.
        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest request) {
        //recupera o token
        var authorizationHeader = request.getHeader("Authorization");
        // agr quem olha se o usuário está logado ou nn é o Spring. Pois foi configurado
        // no SecurityConfigurations/SecurityFilterChain quando usei o authorizeRequests().
        if(authorizationHeader != null){
            //.replace("Bearer", ""), está substituindo a palavra Bearer por nada. Assim será imprimido somente o token.
            return authorizationHeader.replace("Bearer", "");
        }
        return null;
    }

}
