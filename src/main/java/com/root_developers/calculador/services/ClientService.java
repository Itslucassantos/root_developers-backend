package com.root_developers.calculador.services;

import com.root_developers.calculador.dtos.ClientDataDto;

import java.util.UUID;

public interface ClientService {
    ClientDataDto saveLegalClient(ClientDataDto clientDataDto);

    Object savePhysicalClient(ClientDataDto clientDataDto);

    void delete(UUID clientId);
}
