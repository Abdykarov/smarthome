package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.mapper.DeviceMapper;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import eu.lundegaard.smarthome.service.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 1/31/2022 2:33 AM
 */
@Service
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;
    private final DeviceRepository deviceRepository;

    @Override
    public List<DeviceResponseDto> findAllDevices() {
        List<Device> all = deviceRepository.findAll();
        return all.stream()
                .map(deviceMapper::toResponse)
                .toList();
    }

    @Override
    public DeviceResponseDto changeDeviceState(Long deviceId, DeviceState deviceState) {
        Device device = deviceRepository.findById(deviceId);
        if (device == null) {
            throw new DeviceNotFoundException(HttpStatus.NOT_FOUND);
        }
        device.setState(deviceState);
        Device save = deviceRepository.save(device);
        return deviceMapper.toResponse(save);
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
