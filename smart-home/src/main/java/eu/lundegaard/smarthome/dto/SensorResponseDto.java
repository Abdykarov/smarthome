package eu.lundegaard.smarthome.dto;

import eu.lundegaard.smarthome.model.SensorState;
import eu.lundegaard.smarthome.model.SensorType;
import eu.lundegaard.smarthome.observer.DeviceListener;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@Data
@RequiredArgsConstructor
public class SensorResponseDto {

    private SensorType sensorType;
    private SensorState sensorState;
    private List<DeviceDto> connectedDevices;
    @NotEmpty(message = "Please, provide a room name")
    private String room;
}
