package com.root_developers.calculador.services.impl;

import com.root_developers.calculador.dtos.ClientDataDto;
import com.root_developers.calculador.exceptions.ClientException;
import com.root_developers.calculador.exceptions.LegalClientException;
import com.root_developers.calculador.models.AddressModel;
import com.root_developers.calculador.models.LegalClientModel;
import com.root_developers.calculador.models.PhysicalClientModel;
import com.root_developers.calculador.repositories.AddressRepository;
import com.root_developers.calculador.repositories.LegalClientRepository;
import com.root_developers.calculador.repositories.PhysicalClientRepository;
import com.root_developers.calculador.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {
    private final LegalClientRepository legalClientRepository;
    private final PhysicalClientRepository physicalClientRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ClientServiceImpl(LegalClientRepository legalClientRepository, PhysicalClientRepository physicalClientRepository, AddressRepository addressRepository) {
        this.legalClientRepository = legalClientRepository;
        this.physicalClientRepository = physicalClientRepository;
        this.addressRepository = addressRepository;
    }

    @Override
    public ClientDataDto saveLegalClient(ClientDataDto clientDataDto) {
        Optional<LegalClientModel> clientModelOptional = this.legalClientRepository.getReferenceByCnpj(clientDataDto.getCnpj());
        if (clientModelOptional.isPresent()) {
            throw new LegalClientException("Cliente com esse cnpj já existe! ");
        }
        AddressModel addressModel = new AddressModel(clientDataDto.getAddress());
        this.addressRepository.save(addressModel);
        LegalClientModel legalClientModel = new LegalClientModel(clientDataDto, addressModel);
        this.legalClientRepository.save(legalClientModel);
        return new ClientDataDto(legalClientModel);
    }

    @Override
    public Object savePhysicalClient(ClientDataDto clientDataDto) {
        Optional<PhysicalClientModel> clientModelOptional = this.physicalClientRepository.getReferenceByCpf(clientDataDto.getCpf());
        if (clientModelOptional.isPresent()) {
            throw new LegalClientException("Cliente com esse cpf já existe! ");
        }
        AddressModel addressModel = new AddressModel(clientDataDto.getAddress());
        this.addressRepository.save(addressModel);
        PhysicalClientModel physicalClientModel = new PhysicalClientModel(clientDataDto, addressModel);
        this.physicalClientRepository.save(physicalClientModel);
        return new ClientDataDto(physicalClientModel);
    }

    @Override
    public void delete(UUID clientId) {
        Optional<LegalClientModel> legalClientModelOptional = this.legalClientRepository.findById(clientId);
        Optional<PhysicalClientModel> physicalClientModelOptional = this.physicalClientRepository.findById(clientId);
        if(legalClientModelOptional.isPresent()) {
            this.legalClientRepository.delete(legalClientModelOptional.get());
            this.addressRepository.delete(legalClientModelOptional.get().getAddress());
        } else if(physicalClientModelOptional.isPresent()){
            this.physicalClientRepository.delete(physicalClientModelOptional.get());
            this.addressRepository.delete(physicalClientModelOptional.get().getAddress());
        } else {
            throw new ClientException(" O cliente com o id informado não existe! ");
        }
    }

}
