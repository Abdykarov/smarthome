package eu.lundegaard.smarthome.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;

/**
 * @author Ilias Abdykarov
 */
@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SensorDto {
    EventType eventType;
    List<DeviceListener> deviceListenerList;
}
