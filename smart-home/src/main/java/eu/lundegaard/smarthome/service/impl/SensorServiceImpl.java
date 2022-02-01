package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.request.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.response.SensorRequestDto;
import eu.lundegaard.smarthome.dto.response.SensorResponseDto;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.service.SensorService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 2:33 AM
 */
@Service
public class SensorServiceImpl implements SensorService {

    @Override
    public void reactToExternalEvent(Long sensorId, String room, EventType eventType) {

    }

    @Override
    public void changeSensorState(Long sensorId, SensorState state) {

    }

    @Override
    public void createSensor(SensorRequestDto sensorRequestDto) {

    }

    @Override
    public List<DeviceResponseDto> getObservers(Long sensorId) {
        return null;
    }

    @Override
    public void attachSubscriber(Long sensorId, Long listenerId) {

    }

    @Override
    public void detachSubscriber(Long sensorId, Long listenerId) {

    }
}
