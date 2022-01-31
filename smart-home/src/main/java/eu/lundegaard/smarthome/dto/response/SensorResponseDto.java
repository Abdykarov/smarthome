package eu.lundegaard.smarthome.dto.response;

import eu.lundegaard.smarthome.dto.request.DeviceResponseDto;
import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.model.sensor.SensorType;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@Data
@Accessors(chain = true)
public class SensorResponseDto {
    private SensorType sensorType;
    private SensorState sensorState;
    private List<DeviceResponseDto> connectedDevices;
    private String room;
}
