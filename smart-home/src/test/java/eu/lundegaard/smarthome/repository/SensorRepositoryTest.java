package eu.lundegaard.smarthome.repository;

import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.exception.SensorOrRoomNotFoundException;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.model.sensor.SensorType;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Ilias Abdykarov 12.02.2022
 */
@DataJpaTest
class SensorRepositoryTest implements WithAssertions {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(sensorRepository).isNotNull();
        assertThat(entityManager).isNotNull();
    }


    @Test
    void findSensor() {
        UUID id = entityManager.persistAndFlush(getSensor()).getId();

        Optional<Sensor> sensor = sensorRepository.findById(id);
        Sensor sensor1 = sensor.get();

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(sensor1.getRoom()).isEqualTo("Hall");
            softAssertions.assertThat(sensor1.getId()).isEqualTo(id);
        });
    }

    @Test
    void updateDevice() {
        UUID id = entityManager.persistAndFlush(getSensor()).getId();

        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND));

        sensor.setSensorType(SensorType.SMOKE_SENSOR);

        Sensor save = sensorRepository.save(sensor);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(save).isNotNull();
            softAssertions.assertThat(save.getSensorType()).isEqualTo(SensorType.SMOKE_SENSOR);
        });
    }


    @Test
    void createDevice() {
        Sensor sensor = getSensor();

        Sensor save = sensorRepository.save(sensor);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(save).isNotNull();
            softAssertions.assertThat(sensor.getId()).isNotNull();
        });
    }

    @Test
    void deleteDevice() {
        UUID id = entityManager.persistAndFlush(getSensor()).getId();

        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND));

        sensorRepository.deleteById(id);

        assertThat(sensorRepository.findById(id).isEmpty()).isTrue();
    }

    @Test
    void getConnectedDevicesToSensor() {
        Device device = getDevice();
        UUID id = entityManager.persistAndFlush(device).getId();
        UUID sensorId = entityManager.persistAndFlush(getSensor().setConnectedDevices(List.of(device))).getId();

        List<Device> devices = sensorRepository.getConnectedDevices(sensorId);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(devices).isNotNull();
            softAssertions.assertThat(devices.get(0).getId()).isEqualTo(id);
        });
    }

    @Test
    void findByRoom() {
        UUID sensorId1 = entityManager.persistAndFlush(getSensor()).getId();
        UUID sensorId2 = entityManager.persistAndFlush(getSensor()).getId();

        List<Sensor> sensors = sensorRepository.findByRoom("Hall");

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(sensors).isNotNull();
            softAssertions.assertThat(sensors).hasSizeGreaterThan(1);
        });
    }

    @Test
    void findByState() {
        UUID sensorId1 = entityManager.persistAndFlush(getSensor()).getId();
        UUID sensorId2 = entityManager.persistAndFlush(getSensor()).getId();

        List<Sensor> sensors = sensorRepository.getAllByState(SensorState.ACTIVE);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(sensors).isNotNull();
            softAssertions.assertThat(sensors).hasSizeGreaterThan(1);
        });
    }

    private Device getDevice() {
        return new Device()
                .setRoom("Hall")
                .setState(DeviceState.ACTIVE)
                .setDeviceName("Smart doors")
                .setConsumedPower(0)
                .setFunctionalityPercentage(100);
    }

    private Sensor getSensor() {
        return new Sensor()
                .setRoom("Hall")
                .setSensorState(SensorState.ACTIVE)
                .setSensorType(SensorType.MOVEMENT_SENSOR)
                .setConnectedDevices(Arrays.asList());
    }
}