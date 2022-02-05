package eu.lundegaard.smarthome.mapper;

import eu.lundegaard.smarthome.dto.request.DeviceRequestDto;
import eu.lundegaard.smarthome.dto.request.SensorRequestDto;
import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.dto.response.SensorResponseDto;
import eu.lundegaard.smarthome.model.device.Device;
import eu.lundegaard.smarthome.model.device.DeviceState;
import eu.lundegaard.smarthome.model.sensor.Sensor;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.model.sensor.SensorType;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.*;

import java.util.List;

/**
 * @author Ilias Abdykarov, ilias.abdykarov@lundegaard.eu 2/1/2022 4:32 PM
 */
@ExtendWith(MockitoExtension.class)
class SensorMapperTest implements WithAssertions {

    @Mock
    private DeviceMapper deviceMapper;

    @InjectMocks
    private SensorMapperImpl sensorMapper;

    @Test
    void toResponse() {

        Device controlPanel = new Device().setDeviceName("Control panel");
        when(deviceMapper.toResponse(controlPanel)).thenReturn(new DeviceResponseDto().setDeviceName(controlPanel.getDeviceName()));

        Sensor sensor = new Sensor()
                .setConnectedDevices(List.of(controlPanel))
                .setSensorType(SensorType.SMOKE_SENSOR)
                .setRoom("Hall")
                .setSensorState(SensorState.IDLE);

        SensorResponseDto sensorResponseDto = sensorMapper.toResponse(sensor);

        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(sensorResponseDto.getConnectedDevices().get(0).getDeviceName())
                    .isEqualTo("Control panel");
            assertThat(sensorResponseDto.getSensorType()).isEqualTo(SensorType.SMOKE_SENSOR);
            assertThat(sensorResponseDto.getSensorState()).isEqualTo(SensorState.IDLE);
            assertThat(sensorResponseDto.getRoom()).isEqualTo("Hall");
        });
    }

    @Test
    void toEntity() {
        SensorRequestDto sensorRequestDto = new SensorRequestDto()
                .setSensorType(SensorType.WIND_SENSOR)
                .setRoom("Gaming room")
                .setSensorState(SensorState.IDLE);

        Sensor sensor = sensorMapper.toEntity(sensorRequestDto);

        SoftAssertions.assertSoftly(softAssertions -> {
            assertThat(sensor.getRoom()).isEqualTo(sensorRequestDto.getRoom());
            assertThat(sensor.getSensorType()).isEqualTo(sensorRequestDto.getSensorType());
            assertThat(sensor.getSensorState()).isEqualTo(sensorRequestDto.getSensorState());
        });
    }



}