package eu.lundegaard.smarthome.dto.response;

import eu.lundegaard.smarthome.model.sensor.SensorState;
import eu.lundegaard.smarthome.model.sensor.SensorType;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ilias Abdykarov
 */
@Data
@Accessors(chain = true)
public class SensorRequestDto {
    @NotEmpty(message = "Please, provide a sensor type")
    private SensorType sensorType;
    @NotEmpty(message = "Please, provide a sensor state")
    private SensorState sensorState;
    @NotEmpty(message = "Please, provide a room name")
    private String room;
}
