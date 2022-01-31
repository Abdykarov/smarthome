package eu.lundegaard.smarthome.model.device;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Ilias Abdykarov
 */
@Data
@AllArgsConstructor
public abstract class Device {
    private int consumedPower;
    private int functionalityPercentage;
    private String deviceName;
    private DeviceState state;
    private String room;

    public Device() {
    }
}
