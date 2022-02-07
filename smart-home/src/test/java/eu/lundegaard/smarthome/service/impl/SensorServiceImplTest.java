package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.request.SensorRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.response.SensorResponseDto;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.events.WindEvent;
import eu.lundegaard.smarthome.mapper.DeviceMapper;
import eu.lundegaard.smarthome.mapper.SensorMapper;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.model.sensor.SensorType;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import eu.lundegaard.smarthome.repository.SensorRepository;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 5:03 PM
 */
@SpringBootTest
class SensorServiceImplTest implements WithAssertions {

    @MockBean
    private SensorMapper sensorMapper;

    @MockBean
    private DeviceMapper deviceMapper;

    @MockBean
    private SensorRepository sensorRepository;

    @MockBean
    private DeviceServiceImpl deviceService;

    @Autowired
    private SensorServiceImpl sensorService;

    @Test
    void reactToExternalEvent() {
        Sensor sensor = new Sensor()
                .setRoom("Hall")
                .setSensorType(SensorType.WIND_SENSOR)
                .setConnectedDevices(Arrays.asList(new Device().setId(1L).setDeviceName("Smart Windows")));
        List<Sensor> sensors = Arrays.asList(sensor);

        when(sensorRepository.findAllByRoom("Hall"))
                .thenReturn(sensors);

        EventType strongWind = EventType.STRONG_WIND;
        EventDto eventDto = strongWind.returnEvent();
        sensorService.reactToExternalEvent("Hall", strongWind);

        verify(deviceService).notify(1L, eventDto);
        verify(sensorRepository).findAllByRoom("Hall");
    }

    @Test
    void changeSensorState() {
        Sensor sensor = new Sensor()
                .setRoom("Hall")
                .setSensorState(SensorState.BROKEN)
                .setSensorType(SensorType.WIND_SENSOR)
                .setConnectedDevices(Arrays.asList(new Device().setId(1L).setDeviceName("Smart Windows")));
        SensorResponseDto responseDto = new SensorResponseDto().setSensorState(SensorState.ACTIVE);
        Sensor updatedSensor = sensor.setSensorState(SensorState.ACTIVE);

        when(sensorRepository.findById(1L)).thenReturn(sensor);
        when(sensorRepository.save(updatedSensor)).thenReturn(updatedSensor);
        when(sensorMapper.toResponse(updatedSensor)).thenReturn(responseDto);

        SensorResponseDto sensorResponseDto = sensorService.changeSensorState(1L, SensorState.ACTIVE);

        verify(sensorRepository).findById(1L);

        assertThat(sensorResponseDto.getSensorState()).isEqualTo(SensorState.ACTIVE);
    }

    @Test
    void createSensor() {
        SensorRequestDto sensorRequestDto = new SensorRequestDto()
                .setSensorType(SensorType.MOVEMENT_SENSOR);
        Sensor t = new Sensor().setSensorType(SensorType.MOVEMENT_SENSOR);
        when(sensorMapper.toEntity(sensorRequestDto)).thenReturn(t);
        when(sensorRepository.save(t)).thenReturn(t);

        sensorService.createSensor(sensorRequestDto);

        verify(sensorMapper).toEntity(sensorRequestDto);
        verify(sensorRepository).save(t);
    }

    @Test
    void getObservers() {
        Device device = new Device().setDeviceName("TV");
        Sensor tv = new Sensor().setConnectedDevices(Arrays.asList(device));
        DeviceResponseDto deviceResponseDto = new DeviceResponseDto().setDeviceName("TV");

        when(sensorRepository.findById(1L)).thenReturn(tv);
        when(deviceMapper.toResponse(device)).thenReturn(deviceResponseDto);

        List<DeviceResponseDto> observers = sensorService.getObservers(1L);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(observers.size()).isEqualTo(1);
            softAssertions.assertThat(observers.get(0).getDeviceName()).isEqualTo("TV");
        });
    }

    @Test
    void attachSubscriber() {
        // Simple test, this time i decided to write just service implementation without test
    }

    @Test
    void detachSubscriber() {
        // Simple test, this time i decided to write just service implementation without test
    }
}