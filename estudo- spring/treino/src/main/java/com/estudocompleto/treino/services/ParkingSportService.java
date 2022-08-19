package com.estudocompleto.treino.services;

import com.estudocompleto.treino.models.ParkingSportModel;
import com.estudocompleto.treino.repositories.ParkingSportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParkingSportService {
    // responsavel pela a regra de negocio
    // onde coloco if e else e etc ..

    @Autowired // para ingestar no repository no repository
    ParkingSportRepository parkingSportRepository;

    @Transactional // usado principalmente quando tiver um relacionamento
    public Object save(ParkingSportModel parkingSportModel) {
        return parkingSportRepository.save(parkingSportModel);
    }

    public boolean existsByLicensePlateCar(String licensePlateCar) {
        return parkingSportRepository.existsByLicensePlateCar(licensePlateCar);
    }

    public boolean existsByParkingSportNumber(String parkingSportNumber) {
        return parkingSportRepository.existsByParkingSportNumber(parkingSportNumber);
    }

    public boolean existsByLApartmentAndBlock(String apartment, String block) {
        return parkingSportRepository.existsByApartmentAndBlock(apartment, block);
    }

    public List<ParkingSportModel> findAll() {
        return parkingSportRepository.findAll(); // findAll metodo já pronto
    }

    public Optional<ParkingSportModel> findByID(UUID id) {
        return parkingSportRepository.findById(id);
    }
}
