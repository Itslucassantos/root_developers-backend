package com.root_developers.calculador.repositories;

import com.root_developers.calculador.models.LegalClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LegalClientRepository extends JpaRepository<LegalClientModel, UUID> {
    Optional<LegalClientModel> getReferenceByCnpj(String cnpj);
}
