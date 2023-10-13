package com.root_developers.calculador.controller;

import com.root_developers.calculador.dtos.ClientDataDto;
import com.root_developers.calculador.services.ClientService;
import jakarta.validation.Valid;
import org.hibernate.cache.spi.entry.StructuredCacheEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/legalClient")
    public ResponseEntity saveLegalClient(@RequestBody @Validated(ClientDataDto.ClientView.LegalClient.class)
                                              ClientDataDto clientDataDto) {
            return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.saveLegalClient(clientDataDto));
    }

    @PostMapping("/physicalClient")
    public ResponseEntity savePhysicalClient(@RequestBody @Validated(ClientDataDto.ClientView.PhysicalClient.class)
                                                 ClientDataDto clientDataDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(this.clientService.savePhysicalClient(clientDataDto));
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity deleteClient(@PathVariable(value = "clientId") UUID clientId) {
        this.clientService.delete(clientId);
        return ResponseEntity.status(HttpStatus.OK).body(" Cliente deletado com sucesso! ");
    }


}
