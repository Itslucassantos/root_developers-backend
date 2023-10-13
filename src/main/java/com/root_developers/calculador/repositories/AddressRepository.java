package com.root_developers.calculador.repositories;

import com.root_developers.calculador.models.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressModel, UUID> {

//    @Query(value = "select * from tb_addresses a where a.public_place = :publicPlace and a.\"number\" = :number;")
//    Optional<AddressModel> findByPublicPlaceAndNumber(String publicPlace, String number);

}
