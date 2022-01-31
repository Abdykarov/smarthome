package eu.lundegaard.smarthome.service;

import eu.lundegaard.smarthome.dto.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.SensorResponseDto;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.model.SensorState;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
public interface SensorService {

    /**
     * Makes all sensors placed in the room to notify all attached devices to do some action depending on event
     * @param sensorId - id of sensor
     * @param room - name of room
     * @param eventType - type of event
     */
    void reactToExternalEvent(Long sensorId, String room, EventType eventType);

    /**
     * Method changes sensor state
     * @param sensorId - id of sensor
     * @param state - new state to set
     */
    void changeSensorState(Long sensorId, SensorState state);

    /**
     * Method creates a new sensor
     * @param sensorResponseDto - returned sensor dto
     */
    void createSensor(SensorResponseDto sensorResponseDto);

    /**
     * Method returns all installed devices
     * @param sensorId - id of sensor
     * @return a list of device response dtos
     */
    List<DeviceResponseDto> getObservers(Long sensorId);

    /**
     * Method attaches device to the sensor
     * @param sensorId - id of sensor
     * @param listenerId - id of device
     */
    void attachSubscriber(Long sensorId, Long listenerId);

    /**
     * Method detaches an installed device from sensor
     * @param sensorId - id of sensor
     * @param listenerId - id of device
     */
    void detachSubscriber(Long sensorId, Long listenerId);
}
