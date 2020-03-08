package com.newcab.backend_application.controller;

import com.newcab.backend_application.datatransferobject.DriverDTO;
import com.newcab.backend_application.domainobject.DriverDO;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;
import com.newcab.backend_application.mapper.DriverMapper;
import com.newcab.backend_application.service.DriverService;
import com.newcab.backend_application.util.OnlineStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("v1/drivers")
public class DriverController {

    private static final Logger LOG = LoggerFactory.getLogger(DriverController.class);
    private final DriverService driverService;

    @Autowired
    public DriverController(final DriverService driverService) {
        this.driverService = driverService;
    }


    @GetMapping("/{driverId}")
    public DriverDTO getDriver(@PathVariable long driverId) throws EntityNotFoundException {
        LOG.trace("Started get Driver for Driver ID = {}", driverId);
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        LOG.trace("Started Create Driver for Driver Object = {}", driverDTO);
        DriverDO driverDO = DriverMapper.makeDriverDO(driverDTO);
        return DriverMapper.makeDriverDTO(driverService.create(driverDO));
    }


    @DeleteMapping("/{driverId}")
    public void deleteDriver(@PathVariable long driverId) throws EntityNotFoundException {
        LOG.trace("Started Delete Driver for Driver ID = {}", driverId);
        driverService.delete(driverId);
    }


    @PutMapping("/{driverId}")
    public void updateLocation(
            @PathVariable long driverId, @RequestParam double longitude, @RequestParam double latitude)
            throws EntityNotFoundException {
        LOG.trace("Started updateLocation for Driver ID = {}", driverId);
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus) {
        LOG.trace("Started Find Drivers for OnlineStatus = {}", onlineStatus);
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }

    @PostMapping("/{cardId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void selectCar(@Valid @PathVariable long driverId, @RequestParam Integer carId)
            throws ConstraintsViolationException, EntityNotFoundException {
        LOG.trace("Started Select Car for DriverID = {}, CarID = {}", driverId, carId);
        driverService.selectCar(driverId, carId);
    }

    @PostMapping("/{carId}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deSelectCar(@Valid @PathVariable long driverId, @RequestParam Integer carId)
            throws ConstraintsViolationException, EntityNotFoundException {
        LOG.trace("Started DeSelect Car for DriverID = {}, CarID = {}", driverId, carId);
        driverService.deSelectCar(driverId, carId);
    }
}
