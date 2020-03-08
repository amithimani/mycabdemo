package com.newcab.backend_application.service;

import com.newcab.backend_application.dataaccessobject.CarRepository;
import com.newcab.backend_application.domainobject.CarDO;
import com.newcab.backend_application.exception.EntityNotFoundException;
import com.newcab.backend_application.service.DefaultCarServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class DefaultCarServiceTest {

    final CarDO car1 = new CarDO("abc_123", 2, true, 4, "GAS", "BMW");
    DefaultCarServiceImpl defaultCarService;
    @Mock
    private CarRepository mockCarRepository;

    @BeforeEach
    public void setUp() throws Exception {
        defaultCarService = new DefaultCarServiceImpl(mockCarRepository);
        when(mockCarRepository.findById(1L)).thenReturn(Optional.of(car1));
    }

    @Test
    public void throwsExceptionWhenNoRecordsFoundInSearch() throws Exception {
        Assertions.assertThrows(EntityNotFoundException.class, () -> {
            defaultCarService.find(22L);
        });
    }

    @Test
    public void returnsResultWhenOneCarFoundInSearch() throws Exception {
        CarDO resultCar = defaultCarService.find(1L);
        assertEquals(resultCar, car1);
    }

}
