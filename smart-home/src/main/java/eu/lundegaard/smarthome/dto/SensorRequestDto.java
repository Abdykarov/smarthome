package eu.lundegaard.smarthome.dto;

import eu.lundegaard.smarthome.model.SensorState;
import eu.lundegaard.smarthome.model.SensorType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import java.util.List;

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
