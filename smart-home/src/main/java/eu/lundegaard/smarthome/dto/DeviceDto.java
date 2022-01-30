package eu.lundegaard.smarthome.dto;

import eu.lundegaard.smarthome.model.DeviceState;
import eu.lundegaard.smarthome.observer.DeviceListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author Ilias Abdykarov
 */
@Data
@AllArgsConstructor
public class DeviceDto {

    @NotEmpty(message = "Please, provide a power")
    private int consumedPower;
    @NotEmpty(message = "Please, provide a functionality percentage")
    private int functionalityPercentage;
    @NotEmpty(message = "Please, provide a device name")
    private String deviceName;
    @NotEmpty(message = "Please, provide a device state")
    private DeviceState state;

}
