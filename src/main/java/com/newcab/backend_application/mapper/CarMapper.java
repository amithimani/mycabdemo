package com.newcab.backend_application.mapper;

import com.newcab.backend_application.datatransferobject.CarDTO;
import com.newcab.backend_application.domainobject.CarDO;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class CarMapper {
    public static CarDO makeCarDO(CarDTO carDTO) {
        return new CarDO(carDTO.getLicencePlate(), carDTO.getSeatCount(), carDTO.getConvertible(), carDTO.getRating(), carDTO.getEngineType(), carDTO.getManufacturer());
    }

    public static CarDTO makeCarDTO(CarDO carDO) {
        CarDTO.CarDTOBuilder carDTOBuilder = CarDTO.newBuilder()
                .setLicencePlate(carDO.getlicence_plate())
                .setSeatCount(carDO.getseat_count())
                .setConvertible(carDO.getConvertible())
                .setRating(carDO.getRating())
                .setEngineType(carDO.getEngine_type())
                .setManufacturer(carDO.getManufacturer());

        return carDTOBuilder.createCarDTO();
    }


    public static List<CarDTO> makeCarDTOList(Collection<CarDO> cars) {
        return cars.stream()
                .map(CarMapper::makeCarDTO)
                .collect(Collectors.toList());
    }
}
