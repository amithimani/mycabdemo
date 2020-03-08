package com.newcab.backend_application.dataaccessobject;

import com.newcab.backend_application.domainobject.CarDO;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<CarDO, Long> {

}
