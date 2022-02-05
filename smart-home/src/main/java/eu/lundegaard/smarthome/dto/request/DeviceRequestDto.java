package eu.lundegaard.smarthome.dto.request;

import eu.lundegaard.smarthome.model.device.DeviceState;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author Ilias Abdykarov
 */
@Data
@Accessors(chain = true)
public class DeviceRequestDto {
    private int consumedPower;
    private int functionalityPercentage;
    @NotEmpty(message = "Please, provide a device name")
    private String deviceName;
    @NotNull(message = "Please, provide a device state")
    private DeviceState state;
    @NotEmpty(message = "Please, provide a room name")
    private String room;
}
