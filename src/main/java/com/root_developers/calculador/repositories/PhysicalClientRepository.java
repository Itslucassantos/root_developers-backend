package com.root_developers.calculador.repositories;

import com.root_developers.calculador.models.PhysicalClientModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PhysicalClientRepository extends JpaRepository<PhysicalClientModel, UUID> {
    Optional<PhysicalClientModel> getReferenceByCpf(String cpf);
}
