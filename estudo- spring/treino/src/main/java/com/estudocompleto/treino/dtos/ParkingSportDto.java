package com.estudocompleto.treino.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class ParkingSportDto {
    @NotBlank // vai verificar se o campo não é nulo ou vazia
    private String parkingSportNumber;

    @NotBlank
    @Size(max = 7) // elimita o numero de caracterico
    private String licensePlateCar;

    @NotBlank
    private String brandCar;

    @NotBlank
    private String modelCar;

    @NotBlank
    private String colorCar;

    @NotBlank
    private String reponsibleName;

    @NotBlank
    private String apartment;

    @NotBlank
    private String block;
}
