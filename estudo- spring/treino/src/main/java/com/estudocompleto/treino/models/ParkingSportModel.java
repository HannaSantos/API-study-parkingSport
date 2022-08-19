package com.estudocompleto.treino.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity //  slasse que será criada no banco de dados
@Table(name = "TB_PARKING_PORT") // Nome da tabela
@Data // gerar getters e setters
@NoArgsConstructor // contrutor sem argumento
@AllArgsConstructor // construtor com argumento
public class ParkingSportModel implements Serializable {
    private static final long serialVersionUID = 1L; // controle pela jvm

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) // para gerar id automatico
    private UUID id; // para não correr risco de indentificadores

    @Column(nullable = false, unique = true, length = 10) // unique para ser unico
    private String parkingSportNumber; //numero da vaga

    @Column(nullable = false, unique = true, length = 7)
    private String licensePlateCar;

    @Column(nullable = false, length = 70)
    private String brandCar;

    @Column(nullable = false, length = 70)
    private String modelCar;

    @Column(nullable = false, length = 70)
    private String colorCar;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(nullable = false, length = 130)
    private String reponsibleName;

    @Column(nullable = false, length = 30)
    private String apartment;

    @Column(nullable = false, length = 30)
    private String block;
}
