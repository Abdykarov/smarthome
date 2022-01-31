package eu.lundegaard.smarthome.service;

import eu.lundegaard.smarthome.dto.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.DeviceResponseDto;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.model.DeviceState;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
public interface DeviceService {

    /**
     * Returns a list of all devices in house
     * @return a list of device dto
     */
    List<DeviceResponseDto> findAllDevices();

    /**
     * Method finds device by id and changes state
     * @param deviceId an id of existing device
     * @param deviceState a new state to set
     * @return a device response dto
     */
    DeviceResponseDto changeDeviceState(Long deviceId, DeviceState deviceState);

    /**
     * Updates device, if new data are provided
     * @param deviceId - id of device
     * @param deviceDto - provided device data
     * @return a device response dto
     */
    DeviceResponseDto updateDevice(Long deviceId, DeviceRequestDto deviceDto);

    /**
     * Methods notifies device to do some action depending on event
     * @param deviceId - id of device
     * @param eventDto - provided event dto ( event dto may have daughter classes )
     */
    void notify(Long deviceId, EventDto eventDto);

    /**
     * Method creates a new device in house
     * @param deviceDto - provided device data
     */
    void createDevice(DeviceRequestDto deviceDto);

    /**
     * Method deletes device by id
     * @param deviceId - id of device
     */
    void deleteDevice(Long deviceId);
}
