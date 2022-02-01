package eu.lundegaard.smarthome.mapper;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 4:19 PM
 */
class DeviceMapperTest implements WithAssertions {

    private final DeviceMapper productMapper = Mappers.getMapper(DeviceMapper.class);

    @Test
    void toResponse() {
        Device device = new Device()
                .setDeviceName("Smart Windows")
                .setRoom("Gaming room")
                .setState(DeviceState.IDLE);

        DeviceResponseDto deviceResponseDto = productMapper.toResponse(device);

        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(device.getDeviceName()).isEqualTo(deviceResponseDto.getDeviceName());
            assertThat(device.getRoom()).isEqualTo(deviceResponseDto.getRoom());
        });
    }

    @Test
    void toEntity() {
        DeviceRequestDto deviceRequestDto = new DeviceRequestDto()
                .setDeviceName("Smart Windows")
                .setRoom("Gaming room")
                .setState(DeviceState.IDLE);

        Device device = productMapper.toEntity(deviceRequestDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(device.getDeviceName()).isEqualTo(deviceRequestDto.getDeviceName());
            assertThat(device.getRoom()).isEqualTo(deviceRequestDto.getRoom());
        });
    }
}