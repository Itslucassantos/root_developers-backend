package com.root_developers.calculador.services.impl;

import com.root_developers.calculador.dtos.ClientDataDto;
import com.root_developers.calculador.dtos.ClientUpdateDto;
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
    public ClientDataDto savePhysicalClient(ClientDataDto clientDataDto) {
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
        if(legalClientModelOptional.isPresent()) {
            LegalClientModel legalClientModel = legalClientModelOptional.get();
            this.legalClientRepository.delete(legalClientModel);
            this.addressRepository.delete(legalClientModel.getAddress());
        } else {
            Optional<PhysicalClientModel> physicalClientModelOptional = this.physicalClientRepository.findById(clientId);
            if(physicalClientModelOptional.isPresent()){
                PhysicalClientModel physicalClientModel = physicalClientModelOptional.get();
                this.physicalClientRepository.delete(physicalClientModel);
                this.addressRepository.delete(physicalClientModel.getAddress());
            } else {
                throw new ClientException(" O cliente com o id informado não existe! ");
            }
        }
    }

    @Override
    public ClientUpdateDto update(ClientUpdateDto clientUpdateDto) {
        Optional<LegalClientModel> legalClientModelOptional = this.legalClientRepository.findById(clientUpdateDto.getId());
        if(legalClientModelOptional.isPresent()) {
            AddressModel addressModel = legalClientModelOptional.get().getAddress();
            addressModel.update(clientUpdateDto.getAddress());
            this.addressRepository.save(addressModel);
            LegalClientModel legalClientModel = legalClientModelOptional.get();
            legalClientModel.update(clientUpdateDto, addressModel);
            this.legalClientRepository.save(legalClientModel);
            return new ClientUpdateDto(legalClientModel);
        } else {
            Optional<PhysicalClientModel> physicalClientModelOptional = this.physicalClientRepository.findById(clientUpdateDto.getId());
            if (physicalClientModelOptional.isPresent()) {
                AddressModel addressModel = physicalClientModelOptional.get().getAddress();
                addressModel.update(clientUpdateDto.getAddress());
                this.addressRepository.save(addressModel);
                PhysicalClientModel physicalClientModel = physicalClientModelOptional.get();
                physicalClientModel.update(clientUpdateDto, addressModel);
                this.physicalClientRepository.save(physicalClientModel);
                return new ClientUpdateDto(physicalClientModel);
            } else {
                throw new ClientException(" Esse cliente não existe! ");
            }
        }
    }

    @Override
    public ClientDataDto getOneClient(String email) {
        Optional<LegalClientModel> legalClientModelOptional = this.legalClientRepository.findByEmail(email);
        if(legalClientModelOptional.isPresent()) {
            return new ClientDataDto(legalClientModelOptional.get());
        } else {
            Optional<PhysicalClientModel> physicalClientModelOptional = this.physicalClientRepository.findByEmail(email);
            if (physicalClientModelOptional.isPresent()) {
                return new ClientDataDto(physicalClientModelOptional.get());
            } else {
                throw new ClientException(" Cliente não encontrado! ");
            }
        }
    }


}
