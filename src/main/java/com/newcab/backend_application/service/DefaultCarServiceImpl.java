package com.newcab.backend_application.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.newcab.backend_application.dataaccessobject.CarRepository;
import com.newcab.backend_application.domainobject.CarDO;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.rowset.serial.SerialException;

@Service
public class DefaultCarServiceImpl implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarServiceImpl.class);

    private CarRepository carRepository;

    public DefaultCarServiceImpl(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    @HystrixCommand(commandKey=  "get", threadPoolKey="get", fallbackMethod = "fallbackfindCar", ignoreExceptions ={EntityNotFoundException.class} )
    public CarDO find(Long carId) throws EntityNotFoundException {
        return findCarChecked(carId);
    }

    @Override
    @HystrixCommand(commandKey=  "create", threadPoolKey="create", fallbackMethod = "fallbackCreateCar", ignoreExceptions ={ConstraintsViolationException.class} )
    public CarDO create(CarDO carDO) throws ConstraintsViolationException {
        CarDO car;
        LOG.trace("Started Create Car for Car Obj= {}", carDO);
        try {
            car = carRepository.save(carDO);
        } catch (DataIntegrityViolationException e) {
            LOG.error("constraints violation during car creation", e.getMessage());
            throw new ConstraintsViolationException(e.getMessage());
        }
        return car;
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey=  "delete", threadPoolKey="delete", fallbackMethod = "fallbackDeleteCar", ignoreExceptions ={EntityNotFoundException.class} )
    public void delete(Long carId) throws EntityNotFoundException {
        LOG.trace("Started Delete Car for CarId= {}", carId);
        CarDO carDO = findCarChecked(carId);
        carDO.setIs_active(true);
        carRepository.delete(carDO);
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey=  "update", threadPoolKey="update", fallbackMethod = "fallbackUpdateCar", ignoreExceptions ={EntityNotFoundException.class} )
    public void updateCarInfo(long carId, String licencePlate, Integer seatCount, Integer rating, Boolean convertible, String engineType) throws EntityNotFoundException {
        LOG.trace("Started Update Car for Car Obj= {}", carId);
        CarDO carDO = findCarChecked(carId);
        carDO.setlicence_plate(licencePlate);
        carDO.setseat_count(seatCount);
        carDO.setRating(rating);
        carDO.setConvertible(convertible);
        carDO.setEngine_type(engineType);
        carRepository.save(carDO);
    }

    private CarDO findCarChecked(Long carId) throws EntityNotFoundException {
        LOG.trace("Started Find Car for CarID= {}", carId);
        return carRepository.findById(carId)
                .filter(carDO -> carDO.getIs_active())
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + carId));
    }

    //Hystrix FallBack Method
    public CarDO fallbackCreateCar(CarDO carDO){
        throw new RuntimeException(String.format("Exception While trying to create Car {%s}", carDO));
    }

    public CarDO fallbackfindCar(Long carId) {
        throw new RuntimeException(String.format("Exception While trying to Find CarId= {%s}", carId));
    }

    public CarDO fallbackDeleteCar(Long carId) {
        throw new RuntimeException(String.format("Exception While trying to Delete CarId {%s}", carId));
    }

    public void fallbackUpdateCar(long carId, String licencePlate, Integer seatCount, Integer rating, Boolean convertible, String engineType) {
        throw new RuntimeException(String.format("Exception While trying to Update CarId {%s}", carId));
    }

}

