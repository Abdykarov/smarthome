package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.events.WindEvent;
import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.mapper.DeviceMapper;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import eu.lundegaard.smarthome.service.DeviceService;
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
public class DeviceServiceImpl implements DeviceService {

    private final DeviceMapper deviceMapper;
    private final DeviceRepository deviceRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DeviceResponseDto> findAllDevices() {
        List<Device> all = (List<Device>) deviceRepository.findAll();
        return all.stream()
                .map(deviceMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public DeviceResponseDto changeDeviceState(UUID deviceId, DeviceState deviceState) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(HttpStatus.NOT_FOUND));
        device.setState(deviceState);
        Device save = deviceRepository.save(device);
        return deviceMapper.toResponse(save);
    }

    @Override
    @Transactional
    public DeviceResponseDto updateDevice(UUID deviceId, DeviceRequestDto deviceDto) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(HttpStatus.NOT_FOUND));

        Device mapToExistionEntity = deviceMapper.mapToExistionEntity(device, deviceDto);
        Device save = deviceRepository.save(mapToExistionEntity);

        return deviceMapper.toResponse(save);
    }

    @Override
    @Transactional
    public void notify(UUID deviceId, EventDto eventDto) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException(HttpStatus.NOT_FOUND));

        if (eventDto instanceof WindEvent) {
            device.setState(DeviceState.WINDOWS_CLOSED);
            deviceRepository.save(device);
        }
    }

    @Override
    public UUID createDevice(DeviceRequestDto deviceDto) {
        Device device = deviceMapper.toEntity(deviceDto);
        Device save = deviceRepository.save(device);
        return save.getId();
    }

    @Override
    public void deleteDevice(UUID deviceId) {
        if (!deviceRepository.existsById(deviceId)){
            throw new DeviceNotFoundException(HttpStatus.NOT_FOUND);
        }
        deviceRepository.deleteById(deviceId);
    }
}
