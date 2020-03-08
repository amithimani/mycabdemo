package com.newcab.backend_application.controller;

import com.newcab.backend_application.datatransferobject.CarDTO;
import com.newcab.backend_application.domainobject.CarDO;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;
import com.newcab.backend_application.mapper.CarMapper;
import com.newcab.backend_application.service.CarService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * All operations with a car will be routed by this controller.
 * <p/>
 */
@RestController
@RequestMapping("v1/cars")
public class CarController {

    private static final Logger LOG = LoggerFactory.getLogger(CarController.class);
    private final CarService carService;


    @Autowired
    public CarController(final CarService carService) {
        this.carService = carService;
    }


    @GetMapping("/{carId}")
    public CarDTO getCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
        LOG.trace("Started getCar for carID = {}", carId);
        return CarMapper.makeCarDTO(carService.find(carId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarDTO createCar(@Valid @RequestBody CarDTO carDTO) throws ConstraintsViolationException {
        LOG.trace("Started Add Card for car Object = {}", carDTO);
        CarDO carDO = CarMapper.makeCarDO(carDTO);
        return CarMapper.makeCarDTO(carService.create(carDO));
    }


    @DeleteMapping("/{carId}")
    public void deleteCar(@Valid @PathVariable long carId) throws EntityNotFoundException {
        LOG.trace("Started delete Car for carID = {}", carId);
        carService.delete(carId);
    }


    @PutMapping("/{carId}")
    public void updateCarInfo(
            @Valid @PathVariable long carId, String licencePlate, Integer seatCount, Integer rating, Boolean convertible, String engineType)
            throws ConstraintsViolationException, EntityNotFoundException {
        LOG.trace("Started Update Car for carID = {}", carId);
        carService.updateCarInfo(carId, licencePlate, seatCount, rating, convertible, engineType);
    }

}

