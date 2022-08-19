package com.estudocompleto.treino.repositories;

import com.estudocompleto.treino.models.ParkingSportModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ParkingSportRepository extends JpaRepository<ParkingSportModel, UUID> { // aqui passa a model e o indentificador que estamos usando
    // para ter acesso ao banco

    boolean existsByLicensePlateCar(String licensePlateCar); // recebendo aqui a placa do carro para ser validada
    boolean existsByParkingSportNumber(String parkingSportNumber);
    boolean existsByApartmentAndBlock (String apartment, String block);
}
