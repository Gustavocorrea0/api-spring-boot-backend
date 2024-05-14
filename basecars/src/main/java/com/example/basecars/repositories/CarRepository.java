package com.example.basecars.repositories;

import com.example.basecars.models.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<CarModel, UUID> {
    //Optional<CarModel> findByNameCar(String car); //Buscar por nome
}
