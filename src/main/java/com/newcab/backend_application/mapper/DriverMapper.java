package com.newcab.backend_application.mapper;

import com.newcab.backend_application.datatransferobject.DriverDTO;
import com.newcab.backend_application.domainobject.DriverDO;
import com.newcab.backend_application.util.GeoCoordinate;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class DriverMapper {
    public static DriverDO makeDriverDO(DriverDTO driverDTO) {
        return new DriverDO(driverDTO.getUsername(), driverDTO.getPassword());
    }


    public static DriverDTO makeDriverDTO(DriverDO driverDO) {
        DriverDTO.DriverDTOBuilder driverDTOBuilder = DriverDTO.newBuilder()
                .setId(driverDO.getId())
                .setPassword(driverDO.getPassword())
                .setUsername(driverDO.getUsername());

        GeoCoordinate coordinate = driverDO.getCoordinate();
        if (coordinate != null) {
            driverDTOBuilder.setCoordinate(coordinate);
        }

        return driverDTOBuilder.createDriverDTO();
    }


    public static List<DriverDTO> makeDriverDTOList(Collection<DriverDO> drivers) {
        return drivers.stream()
                .map(DriverMapper::makeDriverDTO)
                .collect(Collectors.toList());
    }
}
