package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.service.DeviceService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 2:33 AM
 */
@Service
public class DeviceServiceImpl implements DeviceService {

    @Override
    public List<DeviceResponseDto> findAllDevices() {
        return null;
    }

    @Override
    public DeviceResponseDto changeDeviceState(Long deviceId, DeviceState deviceState) {
        return null;
    }

    @Override
    public DeviceResponseDto updateDevice(Long deviceId, DeviceRequestDto deviceDto) {
        return null;
    }

    @Override
    public void notify(Long deviceId, EventDto eventDto) {

    }

    @Override
    public void createDevice(DeviceRequestDto deviceDto) {

    }

    @Override
    public void deleteDevice(Long deviceId) {

    }
}
