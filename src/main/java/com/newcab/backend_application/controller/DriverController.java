package com.newcab.backend_application.controller;

import com.newcab.backend_application.datatransferobject.DriverDTO;
import com.newcab.backend_application.domainobject.DriverDO;
import com.newcab.backend_application.exception.ConstraintsViolationException;
import com.newcab.backend_application.exception.EntityNotFoundException;
import com.newcab.backend_application.mapper.DriverMapper;
import com.newcab.backend_application.service.DriverService;
import com.newcab.backend_application.util.OnlineStatus;
import org.hibernate.validator.constraints.Range;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;
import java.util.Random;

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
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return DriverMapper.makeDriverDTO(driverService.find(driverId));
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DriverDTO createDriver(@Valid @RequestBody DriverDTO driverDTO) throws ConstraintsViolationException {
        LOG.trace("Started Create Driver for Driver Object = {}", driverDTO);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
            @PathVariable long driverId, @Range(min= -180, max = 180) @RequestParam double longitude, @Range (min= -90, max = 90) @RequestParam double latitude)
            throws EntityNotFoundException {
        LOG.trace("Started updateLocation for Driver ID = {}", driverId);
        driverService.updateLocation(driverId, longitude, latitude);
    }


    @GetMapping
    public List<DriverDTO> findDrivers(@RequestParam OnlineStatus onlineStatus) {

        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOG.trace("Started Find Drivers for OnlineStatus = {}", onlineStatus);
        return DriverMapper.makeDriverDTOList(driverService.find(onlineStatus));
    }

    @PostMapping("/select")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void selectCar(@Valid @RequestParam long driverId, @RequestParam Integer carId)
            throws ConstraintsViolationException, EntityNotFoundException {
        LOG.trace("Started Select Car for DriverID = {}, CarID = {}", driverId, carId);
        driverService.selectCar(driverId, carId);
    }

    @PostMapping("/deselect")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void deSelectCar(@Valid @RequestParam long driverId, @RequestParam Integer carId)
            throws ConstraintsViolationException, EntityNotFoundException {
        LOG.trace("Started DeSelect Car for DriverID = {}, CarID = {}", driverId, carId);
        driverService.deSelectCar(driverId, carId);
    }
}

