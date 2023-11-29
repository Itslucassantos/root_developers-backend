package com.root_developers.calculador.services.impl;

import com.root_developers.calculador.models.LegalClientModel;
import com.root_developers.calculador.models.PhysicalClientModel;
import com.root_developers.calculador.repositories.LegalClientRepository;
import com.root_developers.calculador.repositories.PhysicalClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AutenticationService implements UserDetailsService {

    private final LegalClientRepository legalClientRepository;
    private final PhysicalClientRepository physicalClientRepository;

    @Autowired
    public AutenticationService(LegalClientRepository legalClientRepository, PhysicalClientRepository physicalClientRepository) {
        this.legalClientRepository = legalClientRepository;
        this.physicalClientRepository = physicalClientRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<LegalClientModel> legalClientEmail = this.legalClientRepository.findByEmail(email);
        if(legalClientEmail.isPresent()) {
            return legalClientEmail.get();
        } else {
            Optional<PhysicalClientModel> physicalClientEmail = this.physicalClientRepository.findByEmail(email);
            return physicalClientEmail.get();
        }
    }
}
