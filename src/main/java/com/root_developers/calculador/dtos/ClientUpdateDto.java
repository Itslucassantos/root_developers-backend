package com.root_developers.calculador.dtos;

import com.root_developers.calculador.models.AddressModel;
import com.root_developers.calculador.models.LegalClientModel;
import com.root_developers.calculador.models.PhysicalClientModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ClientUpdateDto {

    @NotNull
    private UUID id;

    @NotBlank(message = " Telefone não pode ser null")
    private String phoneNumber;

    @NotNull(message = " Endereço não pode ser null")
    private AddressModel address;

    public ClientUpdateDto(LegalClientModel legalClientModel) {
        BeanUtils.copyProperties(legalClientModel, this);
    }

    public ClientUpdateDto(PhysicalClientModel physicalClientModel) {
        BeanUtils.copyProperties(physicalClientModel, this);
    }

}
