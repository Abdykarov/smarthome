package eu.lundegaard.smarthome.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

/**
 * @author Ilias Abdykarov
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceDto implements DeviceListener{

    int consumedPower;
    int functionalityPercentage;
    String deviceName;
    DeviceState state;
    int roomId;
    Long id;

    public DeviceDto(String deviceName, int roomId) {
        this.deviceName = deviceName;
        this.roomId = roomId;
        this.consumedPower = 0;
        this.functionalityPercentage = 100;
    }

    @Override
    public void update(String event) {
    }
}
