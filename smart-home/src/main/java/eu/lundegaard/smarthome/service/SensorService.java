package eu.lundegaard.smarthome.service;

import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.request.SensorRequestDto;
import eu.lundegaard.smarthome.dto.response.SensorResponseDto;
import eu.lundegaard.smarthome.events.EventType;
import eu.lundegaard.smarthome.model.sensor.SensorState;

import java.util.List;
import java.util.UUID;

/**
 * @author Ilias Abdykarov
 */
public interface SensorService {

    /**
     * Makes all sensors placed in the room to notify all attached devices to do some action depending on event
     * @param room - name of room
     * @param eventType - type of event
     */
    void reactToExternalEvent(String room, EventType eventType);

    /**
     * Method changes sensor state
     * @param sensorId - id of sensor
     * @param state - new state to set
     */
    SensorResponseDto changeSensorState(UUID sensorId, SensorState state);

    /**
     * Method creates a new sensor
     * @param sensorRequestDto - returned sensor dto
     */
    UUID createSensor(SensorRequestDto sensorRequestDto);

    /**
     * Method returns all installed devices
     * @param sensorId - id of sensor
     * @return a list of device response dtos
     */
    List<DeviceResponseDto> getObservers(UUID sensorId);

    /**
     * Method attaches device to the sensor
     * @param sensorId - id of sensor
     * @param listenerId - id of device
     */
    void attachSubscriber(UUID sensorId, UUID listenerId);

    /**
     * Method detaches an installed device from sensor
     * @param sensorId - id of sensor
     * @param listenerId - id of device
     */
    void detachSubscriber(UUID sensorId, UUID listenerId);
}
