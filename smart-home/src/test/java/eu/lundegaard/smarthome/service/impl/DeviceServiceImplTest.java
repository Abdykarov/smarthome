package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.events.WindEvent;
import eu.lundegaard.smarthome.exception.DeviceNotFoundException;
import eu.lundegaard.smarthome.mapper.DeviceMapper;
import eu.lundegaard.smarthome.mapper.DeviceMapperImpl;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.repository.DeviceRepository;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 5:03 PM
 */
@SpringBootTest
class DeviceServiceImplTest implements WithAssertions {

    @MockBean
    private DeviceMapper deviceMapper;

    @MockBean
    private DeviceRepository deviceRepository;

    @Autowired
    private DeviceServiceImpl deviceService;

    @Test
    void findAllDevices() {
        Device smartDoors = new Device().setDeviceName("Smart smartDoors");
        DeviceResponseDto responseDto = new DeviceResponseDto().setDeviceName(smartDoors.getDeviceName());

        when(deviceRepository.findAll()).thenReturn(List.of(smartDoors));
        when(deviceMapper.toResponse(smartDoors)).thenReturn(responseDto);

        List<DeviceResponseDto> returnedDevices = deviceService.findAllDevices();

        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(returnedDevices.size()).isEqualTo(1);
            assertThat(returnedDevices.stream().findFirst().get().getDeviceName()).isEqualTo("Smart smartDoors");
        });

        verify(deviceRepository).findAll();
        verify(deviceMapper).toResponse(smartDoors);
    }

    @Test
    void changeDeviceState() {
        Device device = new Device()
                .setId(1L)
                .setState(DeviceState.IDLE);

        when(deviceRepository.findById(1L)).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);
        when(deviceMapper.toResponse(device)).then(a -> {
            DeviceResponseDto deviceResponseDto = new DeviceMapperImpl().toResponse(a.getArgument(0));
            return deviceResponseDto;

        });

        DeviceResponseDto responseDto = deviceService.changeDeviceState(1L, DeviceState.ACTIVE);

        assertThat(responseDto.getState()).isEqualTo(DeviceState.ACTIVE);

        verify(deviceRepository).save(device);
        verify(deviceMapper).toResponse(device);
    }

    @Test
    void updateDevice() {
        DeviceRequestDto deviceRequestDto = new DeviceRequestDto()
                .setDeviceName("TV")
                .setConsumedPower(100);

        Device device = new Device()
                .setId(1L)
                .setConsumedPower(89)
                .setDeviceName("Smart Doors");

        Device updatedDevice = new Device()
                .setId(1L)
                .setConsumedPower(100)
                .setDeviceName("TV");

        DeviceResponseDto responseDto = new DeviceResponseDto()
                .setDeviceName("TV")
                .setConsumedPower(100);

        when(deviceRepository.findById(1L)).thenReturn(device);
        when(deviceMapper.mapToExistionEntity(device, deviceRequestDto)).thenReturn(updatedDevice);
        when(deviceRepository.save(updatedDevice)).thenReturn(updatedDevice);
        when(deviceMapper.toResponse(updatedDevice)).thenReturn(responseDto);

        DeviceResponseDto deviceResponseDto = deviceService.updateDevice(1L, deviceRequestDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            softAssertions.assertThat(deviceResponseDto.getDeviceName()).isEqualTo("TV");
            softAssertions.assertThat(deviceResponseDto.getConsumedPower()).isEqualTo(100);
        });

        verify(deviceMapper).mapToExistionEntity(device, deviceRequestDto);
        verify(deviceRepository).save(updatedDevice);
        verify(deviceMapper).toResponse(updatedDevice);
    }

    @Test
    void testNotify() {
        WindEvent windEvent = new WindEvent();

        Device device = new Device()
                .setDeviceName("Smart Windows");
        Device updatedDevice = device.setState(DeviceState.WINDOWS_CLOSED);

        when(deviceRepository.findById(1L)).thenReturn(device);
        when(deviceRepository.save(updatedDevice)).thenReturn(updatedDevice);

        deviceService.notify(1L, windEvent);

        verify(deviceRepository).findById(1L);
        verify(deviceRepository).save(updatedDevice);
    }

    @Test
    void createDevice() {

        DeviceRequestDto deviceRequestDto = new DeviceRequestDto()
                .setDeviceName("TV")
                .setRoom("Gaming room");

        Device device = new Device()
                .setDeviceName("TV")
                        .setId(1L)
                                .setRoom("Gaming room");
        when(deviceMapper.toEntity(deviceRequestDto)).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);

        deviceService.createDevice(deviceRequestDto);

        verify(deviceMapper).toEntity(deviceRequestDto);
        verify(deviceRepository).save(device);
    }

    @Test
    void deleteDevice() {
        doNothing().when(deviceRepository).deleteById(1L);

        deviceService.deleteDevice(1L);

        verify(deviceRepository).deleteById(1L);
    }

    @Test
    void deleteDevice_NotFound() {
        when(deviceRepository.existsById(1L)).thenReturn(false);

        final DeviceNotFoundException deviceNotFoundException = assertThrows(DeviceNotFoundException.class,
                () -> deviceService.deleteDevice(1L));

        verify(deviceRepository).existsById(1L);
    }
}