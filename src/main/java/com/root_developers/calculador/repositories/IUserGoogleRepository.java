package com.root_developers.calculador.repositories;

import com.root_developers.calculador.models.UserGoogleModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.UUID;

public interface IUserGoogleRepository extends JpaRepository<UserGoogleModel, UUID> {
    Collection<UserGoogleModel> findByEmail(String email);

}
