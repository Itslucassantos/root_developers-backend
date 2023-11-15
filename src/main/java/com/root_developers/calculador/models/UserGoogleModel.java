package com.root_developers.calculador.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@Entity(name = "user_google")
@NoArgsConstructor
public class UserGoogleModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @NotNull(message = "Name is required")
    @Column(name = "name", nullable = false)
    private String name;

    @NotNull(message = "Give name is required")
    @Column(name = "given_ame", nullable = false)
    private String givenName;

    @NotNull(message = "Family name is required")
    @Column(name = "family_name", nullable = false)
    private String familyName;

    @NotNull(message = "E-mail is required")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "picture_url", length = 500)
    private String pictureUrl;

    public UserGoogleModel(OAuth2User user){
        this.name = user.getAttributes().get("name").toString();
        this.email = user.getAttributes().get("email").toString();
        this.familyName = user.getAttributes().get("family_name").toString();
        this.givenName = user.getAttributes().get("given_name").toString();
        this.pictureUrl = user.getAttributes().get("picture").toString();
    }

}
