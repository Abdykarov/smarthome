package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.mapper.DeviceMapper;
import eu.lundegaard.smarthome.mapper.SensorMapper;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import eu.lundegaard.smarthome.repository.SensorRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 5:03 PM
 */
@SpringBootTest
class SensorServiceImplTest {

    @MockBean
    private SensorMapper sensorMapper;

    @MockBean
    private SensorRepository sensorRepository;

    @Autowired
    private SensorServiceImpl sensorService;

    @Test
    void reactToExternalEvent() {
    }

    @Test
    void changeSensorState() {
    }

    @Test
    void createSensor() {
    }

    @Test
    void getObservers() {
    }

    @Test
    void attachSubscriber() {
    }

    @Test
    void detachSubscriber() {
    }
}