package com.root_developers.calculador.services;

import com.root_developers.calculador.dtos.IncomeTaxDto;
import com.root_developers.calculador.dtos.IncomeTaxReturnDto;

public interface IncomeTaxService {
    IncomeTaxReturnDto calculate(IncomeTaxDto incomeTaxDto);
}
