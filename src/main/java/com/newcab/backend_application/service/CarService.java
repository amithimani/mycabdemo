package com.newcab.backend_application.service;

import com.newcab.backend_application.domainobject.CarDO;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;

public interface CarService {
    CarDO find(Long carId) throws EntityNotFoundException;

    CarDO create(CarDO carDO) throws ConstraintsViolationException;

    void delete(Long carId) throws EntityNotFoundException;

    void updateCarInfo(long carId, String licencePlate, Integer seatCount, Integer rating, Boolean convertible, String engineType) throws EntityNotFoundException;

}
