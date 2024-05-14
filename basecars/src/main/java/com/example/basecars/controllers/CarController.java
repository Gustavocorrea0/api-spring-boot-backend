package com.example.basecars.controllers;

import com.example.basecars.dtos.CarRecordDto;
import com.example.basecars.models.CarModel;
import com.example.basecars.repositories.CarRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@RestController
public class CarController {
    @Autowired
    CarRepository carRepository;

    @PostMapping("/cadastrar-carro")
    public ResponseEntity<CarModel> saveNewCar(@RequestBody @Valid CarRecordDto carRecordDto) {
        var carModel = new CarModel();
        BeanUtils.copyProperties(carRecordDto, carModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(carRepository.save(carModel));
    }

    @GetMapping("/listar-carro/todos")
    public ResponseEntity<List<CarModel>> getAllCars() {
        List<CarModel> carsList = carRepository.findAll();
        if (!carsList.isEmpty()) {
            for (CarModel car : carsList) {
                UUID id = car.getIdCar();
                car.add(linkTo(methodOn(CarController.class).getOneCar(id)).withSelfRel());
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(carsList);
    }

    @GetMapping("/listar-carro/{id}")
    public ResponseEntity<Object> getOneCar(@PathVariable(value = "id") UUID id) {
        Optional<CarModel> carSearch = carRepository.findById(id);
        if (carSearch.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found");
        }
        carSearch.get().add(linkTo(methodOn(CarController.class).getAllCars()).withRel("Car list"));
        return ResponseEntity.status(HttpStatus.OK).body(carSearch.get());
    }

    @PutMapping("/atualizar-carro/{id}")
    public ResponseEntity<Object> updateCar(@PathVariable(value = "id") UUID id, @RequestBody @Valid CarRecordDto carRecordDto) {
        Optional<CarModel> carM = carRepository.findById(id);
        if (carM.isEmpty()){
            ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not found");
        }
        var carModel = carM.get();
        BeanUtils.copyProperties(carRecordDto, carModel);
        return ResponseEntity.status(HttpStatus.OK).body(carRepository.save(carModel));
    }

    @DeleteMapping("/remover-carro/{id}")
    public ResponseEntity<Object> deleteCar(@PathVariable(value = "id") UUID id){
        Optional<CarModel> carM = carRepository.findById(id);
        if (carM.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Car not Found");
        }
        carRepository.delete(carM.get());
        return ResponseEntity.status(HttpStatus.OK).body("Car remove with successfully");
    }

}
