package com.newcab.backend_application.service;

import com.newcab.backend_application.dataaccessobject.CarRepository;
import com.newcab.backend_application.domainobject.CarDO;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DefaultCarServiceImpl implements CarService {

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(DefaultCarServiceImpl.class);

    private CarRepository carRepository;

    public DefaultCarServiceImpl(final CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDO find(Long carId) throws EntityNotFoundException {
        return findCarChecked(carId);
    }

    @Override
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
    public void delete(Long carId) throws EntityNotFoundException {
        LOG.trace("Started Delete Car for CarId= {}", carId);
        CarDO carDO = findCarChecked(carId);
        carDO.setIs_active(true);
        carRepository.delete(carDO);
    }

    @Override
    @Transactional
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
}

