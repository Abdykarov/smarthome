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

import java.util.List;

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
    public void reactToExternalEvent(String room, EventType eventType) {
        List<Sensor> allByRoom = sensorRepository.findAllByRoom(room);
        for (Sensor sensor : allByRoom) {
            for (Device device : sensor.getConnectedDevices()) {
                deviceService.notify(device.getId(), eventType.returnEvent());
            }
        }
    }

    @Override
    public SensorResponseDto changeSensorState(Long sensorId, SensorState state) {
        Sensor byId = sensorRepository.findById(sensorId);
        if (byId == null) {
            throw new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND);
        }
        byId.setSensorState(state);
        Sensor save = sensorRepository.save(byId);
        SensorResponseDto sensorResponseDto = sensorMapper.toResponse(save);

        return sensorResponseDto;
    }

    @Override
    public void createSensor(SensorRequestDto sensorRequestDto) {
        Sensor sensor = sensorMapper.toEntity(sensorRequestDto);
        sensorRepository.save(sensor);
    }

    @Override
    public List<DeviceResponseDto> getObservers(Long sensorId) {
        Sensor byId = sensorRepository.findById(sensorId);
        if (byId == null) {
            throw new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND);
        }
        List<Device> connectedDevices = byId.getConnectedDevices();
        return connectedDevices.stream()
                .map(d -> deviceMapper.toResponse(d))
                .toList();
    }

    @Override
    public void attachSubscriber(Long sensorId, Long listenerId) {
        Sensor byId = sensorRepository.findById(sensorId);
        if (byId == null) {
            throw new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND);
        }
        Device device = deviceRepository.findById(listenerId);
        if (device == null) {
            throw new DeviceNotFoundException(HttpStatus.NOT_FOUND);
        }
        byId.getConnectedDevices().add(device);
        sensorRepository.save(byId);
    }

    @Override
    public void detachSubscriber(Long sensorId, Long listenerId) {
        Sensor byId = sensorRepository.findById(sensorId);
        if (byId == null) {
            throw new SensorOrRoomNotFoundException(HttpStatus.NOT_FOUND);
        }
        Device device = deviceRepository.findById(listenerId);
        if (device == null) {
            throw new DeviceNotFoundException(HttpStatus.NOT_FOUND);
        }
        byId.getConnectedDevices().remove(device);
        sensorRepository.save(byId);
    }
}
