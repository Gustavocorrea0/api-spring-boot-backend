package com.example.basecars.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Entity
@Table(name = "TB_CARS")
public class CarModel extends RepresentationModel<CarModel> implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public UUID idCar;
    public String carBrand;
    public String carName;
    public String carColor;
    @Max(value = 2025)
    @Min(value = 1900)
    public int carYear;
    public BigDecimal carValue;

    public UUID getIdCar() {
        return idCar;
    }

    public void setIdCar(UUID idCar) {
        this.idCar = idCar;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public int getCarYear() {
        return carYear;
    }

    public void setCarYear(int carYear) {
        this.carYear = carYear;
    }

    public BigDecimal getCarValue() {
        return carValue;
    }

    public void setCarValue(BigDecimal carValue) {
        this.carValue = carValue;
    }
}
