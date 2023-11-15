package com.root_developers.calculador.federated;

import com.root_developers.calculador.models.UserGoogleModel;
import com.root_developers.calculador.repositories.IUserGoogleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.function.Consumer;

@RequiredArgsConstructor
@Slf4j
public final class UserRepositoryOAuth2UserHandler implements Consumer<OAuth2User> {

    private final IUserGoogleRepository iUserGoogleRepository;

    @Override
    public void accept(OAuth2User user) {
        if (this.iUserGoogleRepository.findByEmail(((DefaultOidcUser) user).getEmail()).isEmpty()) {
            UserGoogleModel googleUser = new UserGoogleModel(user);
            this.iUserGoogleRepository.save(googleUser);
        } else {
            log.info("Welcome {}!", user.getAttributes().get("given_name"));
        }
    }


}
