package eu.lundegaard.smarthome.dto.response;

import eu.lundegaard.smarthome.model.device.DeviceState;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Ilias Abdykarov
 */
@Data
@Accessors(chain = true)
public class DeviceResponseDto {
    private int consumedPower;
    private int functionalityPercentage;
    private String deviceName;
    private DeviceState state;
    private String room;
}
