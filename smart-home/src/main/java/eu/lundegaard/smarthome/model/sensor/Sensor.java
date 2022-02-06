package eu.lundegaard.smarthome.model.sensor;

import eu.lundegaard.smarthome.dto.response.DeviceResponseDto;
import eu.lundegaard.smarthome.model.device.Device;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@Data
@Accessors(chain = true)
public class Sensor {
    private Long id;
    private SensorType sensorType;
    private SensorState sensorState;
    private List<Device> connectedDevices;
    private String room;
}
