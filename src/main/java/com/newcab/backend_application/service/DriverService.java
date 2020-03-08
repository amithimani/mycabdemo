package com.newcab.backend_application.service;

import com.newcab.backend_application.domainobject.DriverDO;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;
import com.newcab.backend_application.util.OnlineStatus;

import java.util.List;

public interface DriverService {
    DriverDO find(Long driverId) throws EntityNotFoundException;

    DriverDO create(DriverDO driverDO) throws ConstraintsViolationException;

    void delete(Long driverId) throws EntityNotFoundException;

    void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException;

    List<DriverDO> find(OnlineStatus onlineStatus);

    void selectCar(Long driverId, Integer carId) throws EntityNotFoundException;

    void deSelectCar(Long driverId, Integer carId) throws EntityNotFoundException;
}
