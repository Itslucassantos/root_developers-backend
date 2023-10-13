package com.root_developers.calculador.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_addresses")
public class AddressModel implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID addressId;

    @Column(name = "state", length = 100, nullable = false)
    private String state;

    @Column(name = "city", length = 100, nullable = false)
    private String city;

    @Column(name = "public_place", length = 200, nullable = false)
    private String publicPlace;

    @Column(name = "neighborhood", length = 150, nullable = false)
    private String neighborhood;

    @Column(name = "number", length = 50, nullable = false)
    private String number;

    @Column(name = "zip_code", length = 8, nullable = false)
    private int zipCode;

    @Column(name = "complement", nullable = true, length = 150)
    private String complement;

    public AddressModel(AddressModel address) {
        BeanUtils.copyProperties(address, this);
    }

}
