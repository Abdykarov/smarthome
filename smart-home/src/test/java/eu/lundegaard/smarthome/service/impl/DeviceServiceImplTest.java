package eu.lundegaard.smarthome.service.impl;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 5:03 PM
 */
@ExtendWith(MockitoExtension.class)
class DeviceServiceImplTest implements WithAssertions {

    @Mock
    private DeviceMapper deviceMapper;

    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
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

    }

    @Test
    void changeDeviceState() {
        Device device = new Device()
                .setId(1L)
                .setState(DeviceState.IDLE);

        when(deviceRepository.findById(1L)).thenReturn(device);
        when(deviceRepository.save(device)).thenReturn(device);
        when(deviceMapper.toResponse(device)).then(a -> new DeviceMapperImpl().toResponse(a.getArgument(0)));

        DeviceResponseDto responseDto = deviceService.changeDeviceState(1L, DeviceState.ACTIVE);

        assertThat(responseDto.getState()).isEqualTo(DeviceState.ACTIVE);
    }

    @Test
    void updateDevice() {
    }

    @Test
    void testNotify() {
    }

    @Test
    void createDevice() {
    }

    @Test
    void deleteDevice() {
    }
}