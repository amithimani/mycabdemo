package com.newcab.backend_application.dataaccessobject;

import com.newcab.backend_application.domainobject.DriverDO;
import com.newcab.backend_application.util.OnlineStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Database Access Object for driver table.
 * <p/>
 */
public interface DriverRepository extends CrudRepository<DriverDO, Long> {

    List<DriverDO> findByOnlineStatus(OnlineStatus onlineStatus);
}
