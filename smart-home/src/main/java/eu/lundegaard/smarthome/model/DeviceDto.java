package eu.lundegaard.smarthome.model;

import eu.lundegaard.smarthome.events.EventDto;
import eu.lundegaard.smarthome.events.GasEvent;
import eu.lundegaard.smarthome.observer.DeviceListener;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

/**
 * @author Ilias Abdykarov
 */
@Data
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceDto implements DeviceListener {

    static Long globalId = 1l;
    int consumedPower;
    int functionalityPercentage;
    String deviceName;
    DeviceState state;
    String room;
    Long id;

    public DeviceDto(String deviceName, String room) {
        this.deviceName = deviceName;
        this.room = room;
        this.id = globalId++;
        this.consumedPower = 0;
        this.functionalityPercentage = 100;
    }

    @Override
    public void update(EventDto eventDto) {
        if (eventDto instanceof GasEvent) {
            ((GasEvent) eventDto).notifyHouseElectricalOutlets();
            ((GasEvent) eventDto).notifyHouseElectricalPanel();
        }
    }
}
