package eu.lundegaard.smarthome.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Ilias Abdykarov
 */
@Data
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HomeDto {
    int floorsCount;
    Map<Integer, String> rooms = new HashMap<Integer, String >();
    List<SensorDto> sensors;
}
