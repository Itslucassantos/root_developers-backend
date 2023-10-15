package com.root_developers.calculador.services;

import com.root_developers.calculador.dtos.ClientDataDto;
import com.root_developers.calculador.dtos.ClientUpdateDto;

import java.util.UUID;

public interface ClientService {
    ClientDataDto saveLegalClient(ClientDataDto clientDataDto);

    ClientDataDto savePhysicalClient(ClientDataDto clientDataDto);

    void delete(UUID clientId);

    ClientUpdateDto update(ClientUpdateDto clientUpdateDto);

    ClientDataDto getOneClient(UUID clientId);
}
