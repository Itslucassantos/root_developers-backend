package com.root_developers.calculador.controller;

import com.root_developers.calculador.dtos.IncomeTaxDto;
import com.root_developers.calculador.services.IncomeTaxService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tax")
public class IncomeTaxController {

    private final IncomeTaxService incomeTaxService;

    @Autowired
    public IncomeTaxController(IncomeTaxService incomeTaxService) {
        this.incomeTaxService = incomeTaxService;
    }

    @PostMapping("/calculate")
    @Transactional
    public ResponseEntity calculateTax(@RequestBody @Valid IncomeTaxDto incomeTaxDto) {
        return ResponseEntity.status(HttpStatus.OK).body(this.incomeTaxService.calculate(incomeTaxDto));
    }


}
