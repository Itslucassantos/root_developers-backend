package com.root_developers.calculador.services.impl;

import com.root_developers.calculador.models.LegalClientModel;
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
        UserDetails legalClientEmail = this.legalClientRepository.findByEmail(email);
        if(legalClientEmail != null) {
            return legalClientEmail;
        } else {
            return this.physicalClientRepository.findByEmail(email);
        }
    }
}
