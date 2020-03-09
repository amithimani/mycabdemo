package com.newcab.backend_application.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.newcab.backend_application.dataaccessobject.DriverRepository;
import com.newcab.backend_application.domainobject.CarDO;
import com.newcab.backend_application.domainobject.DriverDO;
import com.newcab.backend_application.exception.CarAlreadyInUseException;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;
import com.newcab.backend_application.util.GeoCoordinate;
import com.newcab.backend_application.util.OnlineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Service to encapsulate the link between DAO and controller and to have business logic for some driver specific things.
 * <p/>
 */
@Service
public class DefaultDriverServiceImpl implements DriverService {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultDriverServiceImpl.class);

    private final DriverRepository driverRepository;


    public DefaultDriverServiceImpl(final DriverRepository driverRepository) {
        this.driverRepository = driverRepository;
    }


    /**
     * Selects a driver by id.
     *
     * @param driverId
     * @return found driver
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @HystrixCommand(commandKey=  "get", threadPoolKey="get", fallbackMethod = "fallbackfindDriver", ignoreExceptions ={EntityNotFoundException.class} )
    public DriverDO find(Long driverId) throws EntityNotFoundException {
        return findDriverChecked(driverId);
    }


    /**
     * Creates a new driver.
     *
     * @param driverDO
     * @return
     * @throws ConstraintsViolationException if a driver already exists with the given username, ... .
     */
    @Override
    @HystrixCommand(commandKey=  "create", threadPoolKey="create", fallbackMethod = "fallbackCreateDriver", ignoreExceptions ={ConstraintsViolationException.class} )
    public DriverDO create(DriverDO driverDO) throws ConstraintsViolationException {
        DriverDO driver;
        try {
            driver = driverRepository.save(driverDO);
        } catch (DataIntegrityViolationException e) {
            LOG.warn("ConstraintsViolationException while creating a driver: {}", driverDO, e);
            throw new ConstraintsViolationException(e.getMessage());
        }
        return driver;
    }


    /**
     * Deletes an existing driver by id.
     *
     * @param driverId
     * @throws EntityNotFoundException if no driver with the given id was found.
     */
    @Override
    @Transactional
    @HystrixCommand(commandKey=  "delete", threadPoolKey="delete", fallbackMethod = "fallbackDeleteDriver", ignoreExceptions ={EntityNotFoundException.class} )
    public void delete(Long driverId) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setDeleted(true);
        driverRepository.save(driverDO);
    }


    /**
     * Update the location for a driver.
     *
     * @param driverId
     * @param longitude
     * @param latitude
     * @throws EntityNotFoundException
     */
    @Override
    @Transactional
    @HystrixCommand(commandKey=  "update", threadPoolKey="update", fallbackMethod = "fallbackUpdateDriver", ignoreExceptions ={EntityNotFoundException.class} )
    public void updateLocation(long driverId, double longitude, double latitude) throws EntityNotFoundException {
        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCoordinate(new GeoCoordinate(latitude, longitude));
        driverRepository.save(driverDO);
    }


    /**
     * Find all drivers by online state.
     *
     * @param onlineStatus
     */
    @Override
    @HystrixCommand(commandKey=  "get", threadPoolKey="get", fallbackMethod = "fallbackfindDriverBasedOnStatus", ignoreExceptions ={EntityNotFoundException.class} )
    public List<DriverDO> find(OnlineStatus onlineStatus) {

        return driverRepository.findByOnlineStatus(onlineStatus);
    }


    private DriverDO findDriverChecked(Long driverId) throws EntityNotFoundException {
        return driverRepository.findById(driverId)
                .orElseThrow(() -> new EntityNotFoundException("Could not find entity with id: " + driverId));
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey=  "get", threadPoolKey="get", fallbackMethod = "fallbackSelectCar", ignoreExceptions ={EntityNotFoundException.class} )
    public void selectCar(Long driverId, Integer carId) throws EntityNotFoundException {

        DriverDO driverDO = findDriverChecked(driverId);
        if (driverDO.getCarIdSelected() != null) {
            throw new CarAlreadyInUseException();
        }
        driverDO.setCarIdSelected(carId);
        driverRepository.save(driverDO);
    }

    @Override
    @Transactional
    @HystrixCommand(commandKey=  "get", threadPoolKey="get", fallbackMethod = "fallbackSelectCar", ignoreExceptions ={EntityNotFoundException.class} )
    public void deSelectCar(Long driverId, Integer carId) throws EntityNotFoundException {

        DriverDO driverDO = findDriverChecked(driverId);
        driverDO.setCarIdSelected(null);
        driverRepository.save(driverDO);

    }

    //Hystrix FallBack Method
    public CarDO fallbackCreateDriver(DriverDO driverDO){
        throw new RuntimeException(String.format("Exception While trying to create Driver {%s}" ,driverDO));
    }

    public CarDO fallbackUpdateDriver(long driverId, double longitude, double latitude){
        throw new RuntimeException(String.format("Exception While trying to update Driver location for driverID {%s}" ,driverId));
    }

    public void fallbackDeleteDriver(Long driverId){
        throw new RuntimeException(String.format("Exception While trying to delete Driver with driverID= {%s}" ,driverId));
    }


    public void fallbackfindDriver(Long driverId){
        throw new RuntimeException(String.format("Exception While trying to find Driver with driverID= {%s}" ,driverId));
    }

    public List<DriverDO> fallbackfindDriverBasedOnStatus(OnlineStatus onlineStatus){
        throw new RuntimeException(String.format("Exception While trying to find Driver with status= {%s}" ,onlineStatus));
    }

    public void fallbackSelectCar(Long driverId, Integer carId){
        throw new RuntimeException(String.format("Exception While trying to select Car operation, Driverid ={%s} and CardID={%s}" ,driverId, carId));
    }
}
