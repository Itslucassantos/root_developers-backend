package com.root_developers.calculador.models;

import com.root_developers.calculador.dtos.ClientDataDto;
import com.root_developers.calculador.dtos.ClientUpdateDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.Serial;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_physical_clients")
// UserDetails é para verificar se o Email e senha pertence a essa classe.
public class PhysicalClientModel implements UserDetails, IClientModel {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID physicalClientId;

    @Column(name = "first_name", length = 100, nullable = false)
    private String firstName;

    @Column(name = "surname", length = 150, nullable = false)
    private String surname;

    @Column(name = "email", nullable = false, unique = true, length = 150)
    private String email;

    @Column(name = "confirm_email", nullable = false, unique = true, length = 150)
    private String confirmEmail;

    @Column(name = "cpf", nullable = false, unique = true, length = 11)
    private String cpf;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "password", nullable = false, length = 255)
    private String password;

    @Column(name = "confirm_password", nullable = false, length = 255)
    private String confirmPassword;

    @OneToOne @JoinColumn(name = "address_id")
    private AddressModel address;

    public PhysicalClientModel(ClientDataDto clientDataDto, AddressModel addressModel) {
        BeanUtils.copyProperties(clientDataDto, this);
        this.password = new BCryptPasswordEncoder().encode(clientDataDto.getPassword().trim());
        this.confirmPassword = this.password;
        this.address = addressModel;
    }

    public void update(ClientUpdateDto clientUpdateDto, AddressModel addressModel) {
        BeanUtils.copyProperties(clientUpdateDto, this);
        this.address = addressModel;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_PHYSICAL_CLIENT"));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
