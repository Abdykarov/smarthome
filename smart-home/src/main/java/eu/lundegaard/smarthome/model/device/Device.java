package eu.lundegaard.smarthome.model.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Ilias Abdykarov
 */
@Data
@Accessors(chain = true)
public class Device {
    private Long id;
    private int consumedPower = 0;
    private int functionalityPercentage = 100;
    private String deviceName;
    private DeviceState state;
    private String room;
}
