package eu.lundegaard.smarthome.dto.request;

import eu.lundegaard.smarthome.model.device.DeviceState;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ilias Abdykarov
 */
@Data
@Accessors(chain = true)
public class DeviceRequestDto {
    @NotEmpty(message = "Please, provide a power")
    private int consumedPower;
    @NotEmpty(message = "Please, provide a functionality percentage")
    private int functionalityPercentage;
    @NotEmpty(message = "Please, provide a device name")
    private String deviceName;
    @NotEmpty(message = "Please, provide a device state")
    private DeviceState state;
    @NotEmpty(message = "Please, provide a room name")
    private String room;
}
