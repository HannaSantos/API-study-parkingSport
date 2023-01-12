package com.estudocompleto.treino.controllers;


import com.estudocompleto.treino.dtos.ParkingSportDto;
import com.estudocompleto.treino.models.ParkingSportModel;
import com.estudocompleto.treino.services.ParkingSportService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/parking-sport")
public class ParkingSportController {
    @Autowired
    ParkingSportService parkingSportService;

    // para salvar no banco
    @PostMapping // para enviar a url
    public ResponseEntity<Object> saveParkingSport(@RequestBody @Valid ParkingSportDto parkingSportDto){ // método publico, reotrno reponse entity, e object pq vamos ter diferente de retorno que vai receber o metodo, valid para bater junto com o dto no notblack
       if (parkingSportService.existsByLicensePlateCar(parkingSportDto.getLicensePlateCar())){
           return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: License plate car is already in use!");
       }
        if (parkingSportService.existsByParkingSportNumber(parkingSportDto.getParkingSportNumber())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Sport is already in use!");
        }
        if (parkingSportService.existsByLApartmentAndBlock(parkingSportDto.getApartment(), parkingSportDto.getBlock())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Parking Sport already registered for this apartment/block!");
        }

        var parkingSportModel = new ParkingSportModel(); // vai instanciar a classe model

        BeanUtils.copyProperties(parkingSportDto, parkingSportModel); // para converter de dto para model, esse bean util é ultio para convenção 1° o que vai ser convertido e 2° como convertido
        parkingSportModel.setRegistrationDate(LocalDateTime.now(ZoneId.of("UTC"))); // estou setando a data para gerar altomatico
        return ResponseEntity.status(HttpStatus.CREATED).body(parkingSportService.save(parkingSportModel)); // retorno que vai ser enviado no service
    }

    @GetMapping
    public ResponseEntity<List<ParkingSportModel>> getAllParkingSports(){ // listar todos os dados da db para api, metodo publico
        return ResponseEntity.status(HttpStatus.OK).body(parkingSportService.findAll());
    }

    @GetMapping("/{id}") // aqui vai precisar do id, para buscar apenas um dados
    public ResponseEntity<Object> getOneParkingSpot(@PathVariable(value = "id") UUID id){ // metodo public, ele vai ter object pq se não existir ele vai ter que retornar um aviso
        Optional<ParkingSportModel> parkingSportModelOptional = parkingSportService.findByID(id); // vai criar um metodo no service

        if (!parkingSportModelOptional.isPresent()){ // verificar se ele não estiver presente
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Parking Sport not found");
        }

        return ResponseEntity.status(HttpStatus.OK).body(parkingSportModelOptional.get());
    }
}
