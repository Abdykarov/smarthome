package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.request.SensorRequestDto;
import eu.lundegaard.smarthome.dto.response.SensorResponseDto;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.exception.SensorOrRoomNotFoundException;
import eu.lundegaard.smarthome.mapper.DeviceMapper;
import eu.lundegaard.smarthome.mapper.SensorMapper;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import eu.lundegaard.smarthome.repository.SensorRepository;
import eu.lundegaard.smarthome.service.SensorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 2:33 AM
 */
@Service
@RequiredArgsConstructor
public class SensorServiceImpl implements SensorService {

    private final SensorMapper sensorMapper;
    private final DeviceMapper deviceMapper;
    private final SensorRepository sensorRepository;
    private final DeviceRepository deviceRepository;
    private final DeviceServiceImpl deviceService;


    @Override
    @Transactional(readOnly = true)
    public void reactToExternalEvent(String room, EventType eventType) {
        List<Sensor> allByRoom = sensorRepository.findAllByRoom(room);
        for (Sensor sensor : allByRoom) {
            for (Device device : sensor.getConnectedDevices()) {
                deviceService.notify(device.getId(), eventType.returnEvent());
            }
        }
    }

    @Override
    @Transactional
    public SensorResponseDto changeSensorState(UUID sensorId, SensorState state) {
        Sensor byId = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND));

        byId.setSensorState(state);
        Sensor save = sensorRepository.save(byId);
        SensorResponseDto sensorResponseDto = sensorMapper.toResponse(save);

        return sensorResponseDto;
    }

    @Override
    @Transactional
    public UUID createSensor(SensorRequestDto sensorRequestDto) {
        Sensor sensor = sensorMapper.toEntity(sensorRequestDto);
        Sensor save = sensorRepository.save(sensor);
        return save.getId();
    }

    @Override
    public List<DeviceResponseDto> getObservers(UUID sensorId) {
        Sensor byId = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND));
        List<Device> connectedDevices = byId.getConnectedDevices();
        return connectedDevices.stream()
                .map(d -> deviceMapper.toResponse(d))
                .toList();
    }

    @Override
    public void attachSubscriber(UUID sensorId, UUID listenerId) {
        Sensor byId = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND));

        Device device = deviceRepository.findById(listenerId)
                .orElseThrow(() -> new DeviceNotFoundException(HttpStatus.NOT_FOUND));

        byId.getConnectedDevices().add(device);
        sensorRepository.save(byId);
    }

    @Override
    @Transactional
    public void detachSubscriber(UUID sensorId, UUID listenerId) {
        Sensor byId = sensorRepository.findById(sensorId)
                .orElseThrow(() -> new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND));
        Device device = deviceRepository.findById(listenerId)
                .orElseThrow(() -> new DeviceNotFoundException(HttpStatus.NOT_FOUND));
        byId.getConnectedDevices().remove(device);
        sensorRepository.save(byId);
    }
}
