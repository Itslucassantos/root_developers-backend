package com.root_developers.calculador.models;

import com.root_developers.calculador.dtos.ClientDataDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.io.Serial;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_physical_clients")
public class PhysicalClientModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID physicalClientId;

    @Column(name = "first_name", length = 100, nullable = false)
    protected String firstName;

    @Column(name = "surname", length = 150, nullable = false)
    protected String surname;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    protected String email;

    @Column(name = "confirm_email", nullable = false, unique = true, length = 150)
    protected String confirmEmail;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    protected String cpf;

    @Column(name = "phone_number", length = 20)
    protected String phoneNumber;

    @Column(name = "password", nullable = false, length = 255)
    protected String password;

    @Column(name = "confirm_password", nullable = false, length = 255)
    protected String confirmPassword;

    @OneToOne @JoinColumn(name = "address_id")
    private AddressModel address;

    public PhysicalClientModel(ClientDataDto clientDataDto, AddressModel addressModel) {
        BeanUtils.copyProperties(clientDataDto, this);
        this.address = addressModel;
    }
}
